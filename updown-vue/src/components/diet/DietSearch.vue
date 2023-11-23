<template>
    <div class="diet-search">
        <div class="diet-head">
            <div class="diet-head-category">{{ props.category }}</div>
            <img class="cursor" @click="cancel" 
                src="../../asset/bootstrap-icon/x-lg.svg" alt="검색취소">
        </div>
        <div class="diet-search-input">
            <input type="text" placeholder="음식 이름을 검색해주세요." v-model="word" @keyup.enter="search">
            <img @click="search" id="search-btn" class="cursor" src="../../asset/bootstrap-icon/search.svg"
                alt="검색">
        </div>
        <table class="table" id="table-head">
            <thead>
                <tr>
                    <th class="diet-type">음식명</th>
                    <th class="diet-met">100g당 열량(kcal)</th>
                </tr>
            </thead>
        </table>
        <div class="search-find" :class="{find : ok}" v-if="searchList==''">검색 결과가 없습니다.</div>
        <div v-else class="box" style="height: 11rem; overflow-y: auto; overflow-x: hidden;">
            <table class="table" id="diet">
                <tbody>
                    <tr id="diet-content" @click="getInfo(info)" class="cursor" v-for="info in searchList" :key="info.type">
                        <td class="diet-type">{{ info.food }}</td>
                        <td class="diet-met">{{ (info.calorie * 100).toFixed(1) }}kcal</td>
                    </tr>
                </tbody>
            </table>
        </div>
    </div>
</template>

<script setup>
import axios from 'axios';
import { ref } from 'vue';

const props = defineProps({
    category: String
})
const API_URL = `http://apis.data.go.kr/1471000/FoodNtrIrdntInfoService1/getFoodNtrItdntList1`

const searchList = ref([]);

const word = ref('');

let ok = ref(false);

const emits = defineEmits(["getInfo", "search", "regist"]);

const getInfo = function (info) {
    searchList.value = [];
    word.value = '';
    emits("getInfo", info);
}

const cancel = function () {
    emits("regist");
}

const search = function () {
    searchList.value = ([]);
    axios.get(API_URL, {
        params: {
            ServiceKey: "s9nr977YLidCuV1NlVpRuFzpmvEqT8y/dHIzz+mh2MnSGTH8uRTydZZbklrRL5wJ960pGV1J46pj6/RrTXXJhQ==",
            desc_kor: word.value,
            type: "json",
        }
    })
    .then((res) => {
        if(!res.data.body.items) {
            ok.value=true;
            setTimeout(function(){
                ok.value=false;
            },3000)
            return;
        }
        const response = res.data.body.items
        for (let i = 0; i < response.length; i++) {
            if(response[i].SERVING_WT == 0) continue;
            searchList.value.push({ food: response[i].DESC_KOR, calorie: (response[i].NUTR_CONT1 / response[i].SERVING_WT).toFixed(1) });
        }
        searchList.value = searchList.value.filter((item, index, self) =>
            index === self.findIndex((t) => (
                t.food === item.food
            ))
        )
    })
}

</script>

<style scoped>
.find{
    color: rgb(189, 0, 0);
}

.search-find{
    font-size: 0.8rem;
    margin-left: 1.5rem;
    margin-top: 0.5rem;
}

.diet-head-category {
    font-size: 1.5rem;
    font-weight: 600;
    padding-left: 0.3em;
}

.diet-head {
    display: flex;
    width: 27rem;
    justify-content: space-between;
    margin-top: 0.3em;
}

.diet-search-input {
    text-align: right;
    margin-top: 0.2rem;
    margin-bottom: 0.1rem;
}
input {
    height: 1.5rem;
    font-size: 0.8rem;
    width: 11rem;
}
input::placeholder {
    font-size: 0.8rem;
}

.table {
    width: 24rem;
    margin-left: 1rem;
    margin-bottom: 0;
}
tr {
    font-size: 0.9rem;
    height: 1rem;
    border-bottom: 0.12em solid #dddcdc;
}
td {
    font-size: 0.7rem;
    height: 1rem;
}
th {
    font-size: 0.7rem;
    background-color: #dddcdc;
}

#diet td {
    font-size: 0.7rem;
}

.diet-type {
    width: 8rem;
    font-size: 0.8rem;
    text-overflow: ellipsis;
    overflow: hidden;
    word-break: break-word;
}
.diet-met {
    font-size: 0.8rem;
    width: 8rem;
}

#diet-content {
    background-color: transparent !important;
    height: 1rem;
}
.box {
    width: 25rem;
}
.box::-webkit-scrollbar { 
    width: 5px; 
}
.box::-webkit-scrollbar-thumb { 
    background-color: #666; 
    border-radius: 10px;
}
.box::-webkit-scrollbar-track { 
    background-color: #dcdcdc;  
    border-radius: 10px;
    box-shadow: inset 0px 0px 5px white;
}
.table>:not(caption)>*>* {
    border-bottom-width: var(--bs-border-width);
    border-bottom-color: white;
}
.cursor {
    width: 2rem;
    margin: 0rem 0.5rem 0.25rem 1rem;
}
#search-btn {
    width: 2rem;
    padding-left: 0.4rem;
    padding-bottom: 0.4rem;
    margin: 0;
    margin-right: 1rem;
}
</style>