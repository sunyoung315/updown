<template>
    <div>
        <h2>운동</h2>
        <img class="cursor" @click="list" style="width: 2.3em;" src="../../asset/icon/list.png" alt="목록">
        <h4>총 운동 시간</h4>
        {{ todayTime }}min
        <h4>총 소모 칼로리</h4>
        {{ todayCalorie.toFixed(1) }}kcal
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

</style>