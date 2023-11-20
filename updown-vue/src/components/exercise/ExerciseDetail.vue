<template>
    <div class="exercise-detail">
        <div class="exercise-head">
            <div class="exercise-title">운동</div>
            <img class="cursor" @click="list" style="width: 46px; height: 46px; margin: 15px 5px 0px 5px;" src="../../asset/bootstrap-icon/table.svg" alt="목록">
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
    padding: 40px;
    border-radius: 20px;
}
.exercise-title {
    font-size: 50px;
    color: white;
}
.exercise-head {
    display: flex;
    justify-content: space-between;
}
.today-exercise-box {
    position: relative;
    top: 60px
}
.today-exercise-key {
    font-size: 30px;
    text-align: center;
}
.today-exercise-value {
    font-size: 80px;
    text-align: center;
    color: white;
}
</style>