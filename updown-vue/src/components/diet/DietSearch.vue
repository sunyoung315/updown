<template>
    <div class="diet-search">
        <div class="diet-head">
            <div class="diet-head-category">{{ props.category }}</div>
            <img class="cursor" @click="cancel" style="width: 43px; margin: 0px 5px 0px 5px;"
                src="../../asset/bootstrap-icon/x-lg.svg" alt="검색취소">
        </div>
        <div class="diet-search-input">
            <input type="text" placeholder="음식 이름을 검색해주세요." v-model="word" @keyup.enter="search">
            <img @click="search" class="cursor" style="width: 40px; margin-left: 5px;" src="../../asset/bootstrap-icon/search.svg"
                alt="검색">
        </div>
        <table class="table">
            <thead>
                <tr>
                    <th class="diet-type">음식명</th>
                    <th class="diet-met">100g당 열량(kcal)</th>
                </tr>
            </thead>
        </table>
        <div class="search-find" :class="{find : ok}" v-if="searchList==''">검색 결과가 없습니다.</div>
        <div v-else class="box" style="height: 250px; overflow-y: auto; overflow-x: hidden;">
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
    color: red;
}

.search-find{
    margin-left: 40px;
    margin-top: 25px;
}

.diet-search {
    padding-bottom: 40px;
    border-radius: 20px;
    height: 380px;
}

.diet-head-category {
    font-size: 40px;
    padding-left: 10px;
}


.diet-head {
    display: flex;
    justify-content: space-between;
}

.diet-search-input {
    text-align: right;
    margin-top: 5px;
    margin-bottom: 5px;
    margin-right: 13px;
}

.table {
    padding-left: 15px;
    padding-right: 15px;
    width: 520px;
    margin-left: 10px;
    margin-right: 10px;
    margin-bottom: 0px;
}
tr {
    height: 30px;
}
td {
    height: 30px;
}
th {
    font-size: 18px;
}

#diet tr td {
    background-color: transparent !important;
    font-size: 18px;
}

.diet-type {
    width: 220px;
}

.diet-met {
    width: 220px;
}

#diet-content {
    background-color: transparent !important;
    height: 30px;
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
    border-bottom-color: rgb(226, 226, 226);
}
</style>