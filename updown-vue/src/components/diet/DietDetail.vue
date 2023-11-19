<template>
    <div>
        <h2>{{ category }}</h2>
        <div v-if="category == '아침'">
            <div>
                <img class="cursor" @click="regist" style="width: 3em;" src="../../asset/icon/add.png" alt="등록">
                <img class="cursor" @click="modify" style="width: 3em;" src="../../asset/icon/edit.png" alt="수정">
                <ul v-for="diet in store.todayDietBreakFast" key="diet.no">
                    <li>
                        <span>{{ diet.food }}</span>>
                        <span>{{ diet.calorie }}</span>>
                    </li>                                   
                </ul>
                <div>
                    <img v-if="store.breakfastimg" style="width: 3em;"  :src="`http://localhost:8080/upload/${store.breakfastimg}`" alt="음식 사진">
                </div>
            </div>         
        </div>

        <div v-else="category == '점심'">
             <div>
                <img class="cursor" @click="regist" style="width: 3em;" src="../../asset/icon/add.png" alt="등록">
                <img class="cursor" @click="modify" style="width: 3em;" src="../../asset/icon/edit.png" alt="수정">
                <ul v-for="diet in store.todayDietLunch" key="diet.no">
                    <li>
                        <span>{{ diet.food }}</span>>
                        <span>{{ diet.calorie }}</span>>
                    </li>                                   
                </ul>
                <div>
                    <img v-if="store.lunchimg" style="width: 3em;"  :src="`http://localhost:8080/upload/${store.lunchimg}`" alt="음식 사진">
                </div>
            </div>       
        </div>

        <!-- 저녁, 간식도 추가하기.. 일단 아침 점심 확인해보고 -->
    </div>
</template>

<script setup>
import { ref, onMounted } from 'vue';
import { useDietStore } from '@/stores/diet';

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

store.getDietBreakFast(loginUserId, regDate);
store.getDietLunch(loginUserId, regDate);



const emits = defineEmits(["modify", "regist", "graph"]);

const modify = function () {
    emits("modify");
}

const regist = function () {
    emits("regist");
}

const graph = function () {
    emits("graph");
}
</script>

<style scoped></style>