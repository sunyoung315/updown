<template>
    <div class="diet-search">
        <div class="diet-head">
            <div class="diet-head-category">{{ props.category }}</div>
            <img class="cursor" @click="cancel" style="width: 43px; margin: 0px 5px 0px 5px;"
                src="../../asset/bootstrap-icon/x-lg.svg" alt="검색취소">
        </div>
        <div class="diet-search-input">
            <input type="text" placeholder="음식 이름을 검색해주세요." v-model="word" @keyup.enter="search">
            <img @click="search" class="cursor" style="width: 2.5em; margin-left: 5px;" src="../../asset/icon/search.png"
                alt="검색">
        </div>
        <table class="table">
            <thead>
                <tr>
                    <th class="name diet-type">음식 이름</th>
                    <th class="name th-diet-met">열량 (1g) </th>
                </tr>
            </thead>
        </table>
        <div class="search-find" :class="{find : ok}" v-if="searchList==''">검색 결과가 없습니다.</div>
        <div v-else class="box" style="height: 386px; overflow-y: auto;">
        <table class="table" id="diet">
                <tbody>
                    <tr id="diet-content" @click="getInfo(info)" class="cursor" v-for="info in searchList" :key="info.type">
                        <td class="diet-type">{{ info.food }}</td>
                        <td class="diet-met">{{ info.calorie }}kcal</td>
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

const searchDiet = ref({
    food: '',
    calorie: '',
});

const word = ref('');

let ok = ref(false);

const emits = defineEmits(["getInfo", "search", "regist"]);

const getInfo = function (info) {
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
                if(response[i].SERVING_WT==0) continue;
                searchDiet.value.food = response[i].DESC_KOR;
                searchDiet.value.calorie = response[i].NUTR_CONT1/response[i].SERVING_WT;
                // 새로운 객체를 생성하여 push
                searchList.value.push({ food: searchDiet.value.food, calorie: searchDiet.value.calorie });
            }
        })
        .then(() => {
            // 중복 제거
            // item: 배열 요소, index: 인덱스, self: 배열 그 자체
            searchList.value = searchList.value.filter((item, index, self) =>
                //현재 요소의 인덱스 (index)와 food와 calorie 값이 같은 첫 번째 요소의 인덱스가 같은지 비교
                //만약 같다면, 그 요소는 중복되지 않은 첫 번째 요소인. 따라서 그 요소는 새로운 배열에 포함
                index === self.findIndex((t) => (
                    t.food === item.food
                ))
            )

            emits("search");
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
    margin-top: 15px;
    margin-bottom: 10px;
    margin-right: 13px;
}

table {
    margin: 15px;
}

th {
    font-size: 18px;
}

#diet tr td {
    background-color: transparent !important;
    font-size: 18px;
    text-overflow: clip;
}

.diet-type {
    width: 250px;
    padding-left: 10px;
    padding-right: 10px;
}

.diet-met {
    width: 300px;
    padding-left: 25px;
    padding-right: 10px;
}

#diet-content {
    background-color: transparent !important;
    height: 55px;
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

.name {
    background-color: rgb(226, 226, 226);
}

.table {
    width: 500px;
    margin: 15px auto;
}

</style>