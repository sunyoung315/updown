<template>
    <div class="exercise-list">
        <div class="exercise-head">
            <div class="exercise-title">운동</div>
            <div>
                <img class="cursor" @click="regist" style="width: 43px; height: 43px; margin: 15px 5px 0px 5px;" src="../../asset/bootstrap-icon/plus-square.svg" alt="등록">
                <img class="cursor" @click="home" style="width: 49px; margin: 15px 5px 0px 5px;" src="../../asset/bootstrap-icon/house.svg" alt="홈으로">
            </div>
        </div>
        <div class="exercise-date">{{ regDate }}</div>
        <div class="exercise-list-name">오늘의 운동 기록</div>
        <table class="table" id="exercise" v-if="todayExerciseList.length">
            <thead>
                <tr>
                    <th class="exercise-type">운동명</th>
                    <th class="exercise-time">운동 시간</th>
                    <th class="exercise-calorie">소모 칼로리</th>
                </tr>
            </thead>
            <tbody>
                <tr v-for="exercise in todayExerciseList" :key="exercise.no">
                    <td>{{ exercise.type }}</td>
                    <td>{{ exercise.time }}min</td>
                    <td>{{ exercise.calorie.toFixed(1) }}kcal</td>
                    <img class="cursor" @click="modify(exercise)" style="width: 40px;" src="../../asset/bootstrap-icon/pencil-square.svg" alt="수정">
                    <img class="cursor" @click="remove(exercise)" style="width: 40px;" src="../../asset/bootstrap-icon/trash3.svg" alt="삭제">
                </tr>
            </tbody>
        </table>
        <div v-else class="no-exercise">등록된 운동 기록이 없습니다.</div>
    </div>
</template>

<script setup>
import { onMounted, computed } from 'vue';
import { useExerciseStore } from '@/stores/exercise';

const today = new Date();
const year = today.getFullYear();
const month = ("0" + (1 + today.getMonth())).slice(-2);
const day = ("0" + today.getDate()).slice(-2);
const regDate = `${year}-${month}-${day}`;

const loginUserId = JSON.parse(localStorage.getItem("loginUser")).id;

const store = useExerciseStore();

onMounted(async () => {
    await store.getExerciseList(loginUserId, regDate);
});

const todayExerciseList = computed(() => store.todayExerciseList);

const emits = defineEmits(["home", "regist", "modify", "remove"]);

const home = function() {
    emits("home");
}

const regist = function() {
    emits("regist");
}

const modify = function(exercise) {
    emits("modify", exercise);
}

const remove = function(exercise) {
    emits("remove", exercise)
}
</script>

<style scoped>
.exercise-list {
    background-color: rgb(91, 94, 151, 0.5);
    padding: 40px;
    border-radius: 20px;
}
.exercise-title {
    font-size: 50px;
}
.exercise-head {
    display: flex;
    justify-content: space-between;
}
.exercise-date {
    margin-top: 20px;
    font-size: 18px;
}
.exercise-list-name {
    font-size: 25px;
    margin-top: 5px;
    margin-bottom: 10px;
}
table {
    margin-top: 0px;
}
th {
    font-size: 16px;
}
#exercise tr td {
    background-color: transparent !important;
    font-size: 18px;
    text-overflow: clip;
}
img {
    background-color: transparent;
}
.exercise-type {
    width: 220px;
}
.exercise-time {
    width: 100px;
}
.exercise-calorie {
    width: 120px;
}
.no-exercise {
    font-size: 18px;
    margin-top: 20px;
}
</style>