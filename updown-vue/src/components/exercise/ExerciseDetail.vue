<template>
    <div class="exercise-detail">
        <div class="exercise-head">
            <div class="exercise-title">운동</div>
            <img class="cursor" @click="list" src="../../asset/bootstrap-icon/table.svg" alt="목록">
        </div>
        <div class="today-exercise-box">
            <p class="today-exercise-key">총 운동 시간</p>
            <p class="today-exercise-value">{{ todayTime }}min</p>
            <p class="today-exercise-key">총 소모 칼로리</p>
            <p class="today-exercise-value">{{ todayCalorie.toFixed(1) }}kcal</p>
        </div>
    </div>
</template>

<script setup>
import { ref, watchEffect, computed } from 'vue';
import { useExerciseStore } from '@/stores/exercise';

const store = useExerciseStore();

const today = new Date();
const year = today.getFullYear();
const month = ("0" + (1 + today.getMonth())).slice(-2);
const day = ("0" + today.getDate()).slice(-2);
const regDate = `${year}-${month}-${day}`;

const loginUserId = JSON.parse(localStorage.getItem("loginUser")).id;

let todayTime = ref(0);
let todayCalorie = ref(0);

const calTotal = function() { 
    todayTime.value = 0;
    todayCalorie.value = 0;
    
    for(let i = 0; i < todayExerciseList.value.length; i++) {
        todayTime.value += todayExerciseList.value[i].time;
        todayCalorie.value += todayExerciseList.value[i].calorie;
    }
}

watchEffect(async () => {
    await store.todayExerciseList;
    await store.getExerciseList(loginUserId, regDate).then(calTotal);
})

let todayExerciseList = computed(() => store.todayExerciseList);

const emits = defineEmits(["list", "regist"]);

const list = function() {
    emits("list");
}

</script>

<style scoped>
.exercise-detail {
    background-color: rgb(91, 94, 151);
    padding: 2rem;
    border-radius: 1rem;
    margin: 1rem;
}
.exercise-title {
    font-size: xx-large;
    font-weight: bolder;
    color: white;
}
.exercise-head {
    display: flex;
    justify-content: space-between;
}
.today-exercise-box {
    position: relative;
    top: 3.5rem
}
.today-exercise-key {
    font-size: 1rem;
    text-align: center;
    font-weight: 600;
}
.today-exercise-value {
    font-size: 2.8rem;
    font-weight: 600;
    text-align: center;
    margin-bottom: 1.5rem;
    color: white;
}
.cursor {
    width: 2.1rem;
    margin: 0.5rem 0 0.25rem 1rem;
}
p {
    margin-bottom: 0;
}
</style>