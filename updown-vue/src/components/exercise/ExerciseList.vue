<template>
    <div class="exercise-list">
        <h2>운동</h2>
        <img class="cursor" @click="regist" style="width: 3em;" src="../../asset/icon/add.png" alt="등록">
        <img class="cursor" @click="home" style="width: 2.5em;" src="../../asset/icon/home.png" alt="홈으로">
        <div v-if="todayExerciseList.length">
            <div v-for="exercise in todayExerciseList" :key="exercise.no">
                {{ exercise.type }}
                {{ exercise.time }}min
                {{ exercise.calorie.toFixed(1) }}kcal
                <img class="cursor" @click="modify(exercise)" style="width: 2.5em;" src="../../asset/icon/edit.png" alt="수정">
                <img class="cursor" @click="remove(exercise)" style="width: 2.5em;" src="../../asset/icon/trash.png" alt="삭제">
            </div>
        </div>
        <div v-else>등록된 운동 기록이 없습니다.</div>
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

const emits = defineEmits(["home","regist", "modify", "remove"]);

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
    padding: 2em;
    border-radius: 20px;
}
</style>