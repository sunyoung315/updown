<template>
    <div>
        <div class="diet-head">
            <div>
                <span class="diet-head-category">{{ category }}</span>
                <span class="diet-head-calorie">{{ calSum.toFixed(1) }}kcal</span>
            </div>
            <img class="cursor" @click="regist" src="../../asset/bootstrap-icon/plus-square.svg" alt="등록">
        </div>
        <div v-if="category == '아침'">
            <div>
                <div class="diet-table" v-if="store.todayDietBreakFast.length !== 0">
                    <table class="table">
                        <div class="box">
                            <tbody>
                                <tr v-for="diet in store.todayDietBreakFast" key="diet.no">
                                    <td class="diet-col"><img src="../../asset/bootstrap-icon/check-lg.svg" alt="row"></td>  
                                    <td class="diet-food-col">{{ diet.food }}</td>  
                                    <td class="diet-calorie-col">{{ diet.calorie.toFixed(1) }}kcal</td>  
                                    <td><img class="cursor" id="cursor-small" @click="modify(diet)" src="../../asset/bootstrap-icon/pencil-square.svg" alt="수정"></td>
                                    <td><img class="cursor" id="cursor-small" @click="remove(diet)" src="../../asset/bootstrap-icon/trash3.svg" alt="삭제"></td>
                                </tr>
                            </tbody>
                        </div>
                    </table>
                    <div class="diet-img">
                        <img class="food-img" v-if="store.breakfastimg"
                            :src="`http://localhost:8080/upload/${store.breakfastimg}`" alt="음식 사진">
                    </div>
                </div>
                <div v-else class="no-diet">
                    <p>등록된 식단이 없습니다.</p>
                </div>
            </div>
        </div>
        
        <div v-else-if="category == '점심'">
            <div>
                <div class="diet-table" v-if="store.todayDietLunch.length !== 0">
                    <table class="table">
                        <div class="box">
                            <tbody>
                                <tr v-for="diet in store.todayDietLunch" key="diet.no">
                                    <td class="diet-col"><img src="../../asset/bootstrap-icon/check-lg.svg" alt="row"></td>  
                                    <td class="diet-food-col">{{ diet.food }}</td>  
                                    <td class="diet-calorie-col">{{ diet.calorie }}kcal</td>  
                                    <td><img class="cursor" id="cursor-small" @click="modify(diet)" src="../../asset/bootstrap-icon/pencil-square.svg" alt="수정"></td>
                                    <td><img class="cursor" id="cursor-small" @click="remove(diet)" src="../../asset/bootstrap-icon/trash3.svg" alt="삭제"></td>
                                </tr>
                            </tbody>
                        </div>
                    </table>
                    <div class="diet-img">
                        <img class="food-img" v-if="store.lunchimg"
                            :src="`http://localhost:8080/upload/${store.lunchimg}`" alt="음식 사진">
                    </div>
                </div>
                <div v-else class="no-diet">
                    <p>등록된 식단이 없습니다.</p>
                </div>
            </div>
        </div>

        <div v-else-if="category == '저녁'">
            <div>
                <div class="diet-table" v-if="store.todayDietDinner.length !== 0">
                    <table class="table">
                        <div class="box">
                            <tbody>
                                <tr v-for="diet in store.todayDietDinner" key="diet.no">
                                    <td class="diet-col"><img src="../../asset/bootstrap-icon/check-lg.svg" alt="row"></td>  
                                    <td class="diet-food-col">{{ diet.food }}</td>  
                                    <td class="diet-calorie-col">{{ diet.calorie }}kcal</td>  
                                    <td><img class="cursor" id="cursor-small" @click="modify(diet)" src="../../asset/bootstrap-icon/pencil-square.svg" alt="수정"></td>
                                    <td><img class="cursor" id="cursor-small" @click="remove(diet)" src="../../asset/bootstrap-icon/trash3.svg" alt="삭제"></td>
                                </tr>
                            </tbody>
                        </div>
                    </table>
                    <div class="diet-img">
                        <img class="food-img" v-if="store.dinnerimg"
                            :src="`http://localhost:8080/upload/${store.dinnerimg}`" alt="음식 사진">
                    </div>
                </div>
                <div v-else class="no-diet">
                    <p>등록된 식단이 없습니다.</p>
                </div>
            </div>
        </div>

        <div v-else-if="category == '간식'">
            <div>
                <div class="diet-table" v-if="store.todayDietSnack.length !== 0">
                    <table class="table">
                        <div class="box" style="height: 100em; overflow-y: auto;">
                            <tbody>
                                <tr v-for="diet in store.todayDietSnack" key="diet.no">
                                    <td class="diet-col"><img src="../../asset/bootstrap-icon/check-lg.svg" alt="row"></td>  
                                    <td class="diet-food-col">{{ diet.food }}</td>  
                                    <td class="diet-calorie-col">{{ diet.calorie }}kcal</td>  
                                    <td><img class="cursor" id="cursor-small" @click="modify(diet)" src="../../asset/bootstrap-icon/pencil-square.svg" alt="수정"></td>
                                    <td><img class="cursor" id="cursor-small" @click="remove(diet)" src="../../asset/bootstrap-icon/trash3.svg" alt="삭제"></td>
                                </tr>
                            </tbody>
                        </div>
                    </table>
                    <div class="diet-img">
                        <img class="food-img" v-if="store.snackimg"
                            :src="`http://localhost:8080/upload/${store.snackimg}`" alt="음식 사진">
                    </div>
                </div>
                <div v-else class="no-diet">
                    <p>등록된 식단이 없습니다.</p>
                </div>
            </div>
        </div>
    </div>
</template>

<script setup>
import { useDietStore } from '@/stores/diet';
import { ref, watchEffect} from 'vue';

const store = useDietStore();

const props = defineProps({
    category: String,
})

const today = new Date();
const year = today.getFullYear();
const month = ("0" + (1 + today.getMonth())).slice(-2);
const day = ("0" + today.getDate()).slice(-2);
const regDate = year + '-' + month + '-' + day;
const loginUserId = JSON.parse(localStorage.getItem("loginUser")).id;

const emits = defineEmits(["modify", "regist", "graph", "remove"]);

const regist = function () {
    emits("regist");
}
const modify = function (diet) {
    emits("modify", diet);
}

const remove = function (diet) {
    store.breakfastimg = ''
    emits("remove", diet)
}

const calSum = ref(0);

const sum = function () {
    calSum.value = 0; // 초기화

    let dietList;

    if (props.category == '아침') dietList = store.todayDietBreakFast;
    else if (props.category == '점심') dietList = store.todayDietLunch;
    else if (props.category == '저녁') dietList = store.todayDietDinner;
    else if (props.category == '간식') dietList = store.todayDietSnack;

    for (let i = 0; i < dietList.length; i++) {
        calSum.value += Number(dietList[i].calorie);
    }
}

watchEffect (async() => {
    if (props.category == '아침') { 
        await store.todayDietBreakFast
        await store.getDietBreakFast(loginUserId, regDate).then(sum); 
        await store.breakfastimg;
    }
    else if (props.category == '점심') {
        await store.todayDietLunch
        await store.getDietLunch(loginUserId, regDate).then(sum);
    }
    else if (props.category == '저녁') {
        await store.todayDietDinner
        await store.getDietDinner(loginUserId, regDate).then(sum);
    }
    else if (props.category == '간식') {
        await store.todayDietSnack
        await store.getDietSnack(loginUserId, regDate).then(sum);
    }
    
});


</script>

<style scoped>
.diet-head {
    display: flex;
    width: 27rem;
    justify-content: space-between;
    margin-top: 0.3em;
}
.diet-head-category {
    font-size: 1.5rem;
    font-weight: 600;
    padding-left: 0.3em;
}
.diet-head-calorie {
    font-size: large;
    padding-left: 1em;
}
.no-diet {
    padding-top: 1.5rem;
    padding-left: 1.5rem;
    font-size: medium;
    font-weight: 600;
}

.cursor {
    width: 2rem;
    margin: 0.1rem 0.5rem 0.25rem 1rem;
}
.diet-table {
    margin-top: 1em;
    display: flex;
}
tr {
    font-size: small;
    display: flex;
    height: 2.5em;
    padding: 0.2rem 0.1rem 0.2rem 0.4rem;
}

#cursor-small{
    width: 1.3em;
    margin: 0.2rem;
}
.table {
    width: 14.5em;
}
.diet-food-col {
    width: 8em;
    text-overflow: ellipsis;
    overflow: hidden;
    word-break: break-word;
    display: -webkit-box;
    -webkit-line-clamp: 1;
    -webkit-box-orient: vertical
}
.diet-calorie-col {
    width: 6em;
}
.diet-img{
    width: 10.5em;
    height: 14.3em;
    background-color: rgb(225, 225, 225);
    margin-top: 0.1em;
    line-height: 14.3em;
    vertical-align: middle;
}
.food-img {
    width: 9.5em;
    margin-left: 0.5em;
    margin-right: 0.5em;
}
.box {
    height: 14.3em; 
    overflow-y: auto;
    overflow-x: hidden;
    margin-right: 0.3em
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
</style>