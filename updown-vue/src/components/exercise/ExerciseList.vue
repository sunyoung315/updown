<template>
    <div class="exercise-list">
        <div class="exercise-head">
            <div class="exercise-title">운동</div>
            <div>
                <img class="cursor" @click="regist" src="../../asset/bootstrap-icon/plus-square.svg" alt="등록">
                <img class="cursor" @click="home" id="home-icon" src="../../asset/bootstrap-icon/house.svg" alt="홈으로">
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
                    <img class="cursor" id="tb-btn" @click="modify(exercise)" src="../../asset/bootstrap-icon/pencil-square.svg" alt="수정">
                    <img class="cursor" id="tb-btn" @click="remove(exercise)" src="../../asset/bootstrap-icon/trash3.svg" alt="삭제">
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
    padding: 2rem;
    border-radius: 1rem;
    margin: 1rem;
}
.exercise-title {
    font-size: xx-large;
    font-weight: bolder;
}
.exercise-head {
    display: flex;
    justify-content: space-between;
}
.exercise-date {
    margin-top: 0.5rem;
    margin-left: 0.1rem;
    font-size: 0.9rem;
}
.exercise-list-name {
    font-size: 1rem;
    font-weight: 600;
    margin-top: 0.2rem;
    margin-bottom: 0.4rem;
}
table {
    margin-top: 0;
}
th {
    font-size: 0.9rem;
}
#exercise tr td {
    background-color: transparent !important;
    font-size: 0.9rem;
    text-overflow: clip;
}
img {
    background-color: transparent;
}
.exercise-type {
    width: 9rem;
}
.exercise-time {
    width: 6.7rem;
}
.exercise-calorie {
    width: 6.7rem;
}
.no-exercise {
    font-size: 0.9rem;
    font-weight: bold;
    margin-top: 1rem;
}
.cursor {
    width: 2rem;
    margin: 0.4rem 0.08rem 0.25rem 1rem;
}
#home-icon {
    width: 2.35rem;
    margin: 0.4rem 0 0.25rem 0.7rem;
}
#tb-btn {
    width: 1.5rem;
    margin: 0.52rem 0.2rem 0.52rem;
    padding: 0;
}
img {
    margin: 0;
}
</style>