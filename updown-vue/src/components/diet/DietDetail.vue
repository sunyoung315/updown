<template>
    <div>
        <h2>{{ category }}</h2>
        <div v-if="category == '아침'">
            <div>
                <img class="cursor" @click="regist" style="width: 3em;" src="../../asset/icon/add.png" alt="등록">
                <ul v-for="diet in store.todayDietBreakFast" key="diet.no">
                    <li>
                        <span>{{ diet.food }}</span>
                        <span>{{ diet.calorie }}kcal</span>
                        <img class="cursor" @click="modify(diet)" style="width: 3em;" src="../../asset/icon/edit.png" alt="수정">
                        <img class="cursor" @click="remove(diet)" style="width: 2.5em;" src="../../asset/icon/trash.png" alt="삭제">
                    </li>
                </ul>
                <div>
                    <img v-if="store.breakfastimg" style="width: 3em;"
                        :src="`http://localhost:8080/upload/${store.breakfastimg}`" alt="음식 사진">
                </div>
            </div>
        </div>

        <div v-else-if="category == '점심'">
            <div>
                <img class="cursor" @click="regist" style="width: 3em;" src="../../asset/icon/add.png" alt="등록">
                <ul v-for="diet in store.todayDietLunch" key="diet.no">
                    <li>
                        <span>{{ diet.food }}</span>
                        <span>{{ diet.calorie }}kcal</span>
                        <img class="cursor" @click="modify(diet)" style="width: 3em;" src="../../asset/icon/edit.png" alt="수정">
                        <img class="cursor" @click="remove(diet)" style="width: 2.5em;" src="../../asset/icon/trash.png" alt="삭제">
                    </li>
                </ul>
                <div>
                    <img v-if="store.lunchimg" style="width: 3em;" :src="`http://localhost:8080/upload/${store.lunchimg}`"
                        alt="음식 사진">
                </div>
            </div>
        </div>

        <div v-else-if="category == '저녁'">
            <div>
                <img class="cursor" @click="regist" style="width: 3em;" src="../../asset/icon/add.png" alt="등록">
                <ul v-for="diet in store.todayDietDinner" key="diet.no">
                    <li>
                        <span>{{ diet.food }}</span>
                        <span>{{ diet.calorie }}kcal</span>
                        <img class="cursor" @click="modify(diet)" style="width: 3em;" src="../../asset/icon/edit.png" alt="수정">
                        <img class="cursor" @click="remove(diet)" style="width: 2.5em;" src="../../asset/icon/trash.png" alt="삭제">
                    </li>
                </ul>
                <div>
                    <img v-if="store.dinnerimg" style="width: 3em;" :src="`http://localhost:8080/upload/${store.dinnerimg}`"
                        alt="음식 사진">
                </div>
            </div>
        </div>

        <div v-else-if="category == '간식'">
            <div>
                <img class="cursor" @click="regist" style="width: 3em;" src="../../asset/icon/add.png" alt="등록">
                <ul v-for="diet in store.todayDietSnack" key="diet.no">
                    <li>
                        <span>{{ diet.food }}</span>
                        <span>{{ diet.calorie }}kcal</span>
                        <img class="cursor" @click="modify(diet)" style="width: 3em;" src="../../asset/icon/edit.png" alt="수정">
                        <img class="cursor" @click="remove(diet)" style="width: 2.5em;" src="../../asset/icon/trash.png" alt="삭제">
                    </li>
                </ul>
                <div>
                    <img v-if="store.snackimg" style="width: 3em;" :src="`http://localhost:8080/upload/${store.snackimg}`"
                        alt="음식 사진">
                </div>
            </div>
        </div>
    </div>
</template>

<script setup>
import { useDietStore } from '@/stores/diet';
import { onMounted } from 'vue';

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

// if (props.category == '아침')
//     store.getDietBreakFast(loginUserId, regDate);
// else if (props.category == '점심')
//     store.getDietLunch(loginUserId, regDate);
// else if (props.category == '저녁') 
//     store.getDietDinner(loginUserId, regDate);
// else if (props.category == '간식')
//     store.getDietSnack(loginUserId, regDate);


const emits = defineEmits(["modify", "regist", "graph", "remove"]);


const regist = function () {
    emits("regist");
}
const modify = function(diet) {
    emits("modify", diet);
}

const remove = function(diet) {
    emits("remove", diet)
}

const graph = function () {
    emits("graph");
}

onMounted(()=>{
    if (props.category == '아침')
    store.getDietBreakFast(loginUserId, regDate);
else if (props.category == '점심')
    store.getDietLunch(loginUserId, regDate);
else if (props.category == '저녁') 
    store.getDietDinner(loginUserId, regDate);
else if (props.category == '간식')
    store.getDietSnack(loginUserId, regDate);
})

</script>

<style scoped></style>