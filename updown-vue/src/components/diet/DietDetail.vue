<template>
    <div>
        <span>{{ calSum }}kcal</span>
        <h2>{{ category }}</h2>
        <div v-if="category == '아침'">
            <div>
                <img class="cursor" @click="regist" style="width: 3em;" src="../../asset/icon/add.png" alt="등록">

                <div v-if="store.todayDietBreakFast.length !== 0">
                    <ul v-for="diet in store.todayDietBreakFast" key="diet.no">
                        <li>
                            <span>{{ diet.food }} {{ diet.calorie }}kcal</span>
                            <img class="cursor" @click="modify(diet)" style="width: 3em;" src="../../asset/icon/edit.png"
                                alt="수정">
                            <img class="cursor" @click="remove(diet)" style="width: 2.5em;" src="../../asset/icon/trash.png"
                                alt="삭제">
                        </li>
                    </ul>
                    <div>
                        <img v-if="store.breakfastimg" style="width: 3em;"
                            :src="`http://localhost:8080/upload/${store.breakfastimg}`" alt="음식 사진">
                    </div>
                </div>
                <div v-else>
                    <p>등록된 식단이 없습니다.</p>
                </div>

            </div>
        </div>

        <div v-else-if="category == '점심'">
            <div>
                <img class="cursor" @click="regist" style="width: 3em;" src="../../asset/icon/add.png" alt="등록">
                <div v-if="store.todayDietLunch.length !== 0">
                    <ul v-for="diet in store.todayDietLunch" key="diet.no">
                        <li>
                            <span>{{ diet.food }}</span>
                            <span>{{ diet.calorie }}kcal</span>
                            <img class="cursor" @click="modify(diet)" style="width: 3em;" src="../../asset/icon/edit.png"
                                alt="수정">
                            <img class="cursor" @click="remove(diet)" style="width: 2.5em;" src="../../asset/icon/trash.png"
                                alt="삭제">
                        </li>
                    </ul>
                    <div>
                        <img v-if="store.lunchimg" style="width: 3em;"
                            :src="`http://localhost:8080/upload/${store.lunchimg}`" alt="음식 사진">
                    </div>
                </div>
                <div v-else>
                    <p>등록된 식단이 없습니다.</p>
                </div>
            </div>
        </div>

        <div v-else-if="category == '저녁'">
            <div>
                <img class="cursor" @click="regist" style="width: 3em;" src="../../asset/icon/add.png" alt="등록">
                <div v-if="store.todayDietDinner.length !== 0">
                    <ul v-for="diet in store.todayDietDinner" key="diet.no">
                        <li>
                            <span>{{ diet.food }}</span>
                            <span>{{ diet.calorie }}kcal</span>
                            <img class="cursor" @click="modify(diet)" style="width: 3em;" src="../../asset/icon/edit.png"
                                alt="수정">
                            <img class="cursor" @click="remove(diet)" style="width: 2.5em;" src="../../asset/icon/trash.png"
                                alt="삭제">
                        </li>
                    </ul>
                    <div>
                        <img v-if="store.dinnerimg" style="width: 3em;"
                            :src="`http://localhost:8080/upload/${store.dinnerimg}`" alt="음식 사진">
                    </div>
                </div>
                <div v-else>
                    <p>등록된 식단이 없습니다.</p>
                </div>
            </div>
        </div>

        <div v-else-if="category == '간식'">
            <div>
                <img class="cursor" @click="regist" style="width: 3em;" src="../../asset/icon/add.png" alt="등록">
                <div v-if="store.todayDietSnack.length !== 0">
                    <ul v-for="diet in store.todayDietSnack" key="diet.no">
                        <li>
                            <span>{{ diet.food }}</span>
                            <span>{{ diet.calorie }}kcal</span>
                            <img class="cursor" @click="modify(diet)" style="width: 3em;" src="../../asset/icon/edit.png"
                                alt="수정">
                            <img class="cursor" @click="remove(diet)" style="width: 2.5em;" src="../../asset/icon/trash.png"
                                alt="삭제">
                        </li>
                    </ul>
                    <div>
                        <img v-if="store.snackimg" style="width: 3em;"
                            :src="`http://localhost:8080/upload/${store.snackimg}`" alt="음식 사진">
                    </div>
                </div>
                <div v-else>
                    <p>등록된 식단이 없습니다.</p>
                </div>
            </div>
        </div>
    </div>
</template>

<script setup>
import { useDietStore } from '@/stores/diet';
import { ref, watchEffect } from 'vue';

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

const emits = defineEmits(["modify", "regist", "graph", "remove", "caloriesum"]);


const regist = function () {
    emits("regist");
}
const modify = function (diet) {
    emits("modify", diet);
}

const remove = function (diet) {
    emits("remove", diet)
}

const caloriesum = function(calSum){
    emits("caloriesum", calSum)
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

watchEffect(async() => {
    if (props.category == '아침') { 
        await store.todayDietBreakFast
        await store.getDietBreakFast(loginUserId, regDate).then(sum); 
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

<style scoped></style>