<template>
    <div id="diet-container">
        <div class="diet-head">
            <div class="diet-title">식단</div>
            <div class="diet-calorie">총 섭취 칼로리 : {{ totalCalorie.toFixed(1) }} kcal</div>
        </div>
        <div>
            <div class="diet-box">
                <DietBreakFast />
                <DietLunch />
            </div>
            <div class="diet-box">
                <DietDinner />
                <DietSnack />
            </div>
        </div>
    </div>
</template>


<script setup>
import DietBreakFast from '@/components/diet/DietBreakFast.vue';
import DietLunch from '@/components/diet/DietLunch.vue';
import DietDinner from '@/components/diet/DietDinner.vue';
import DietSnack from '@/components/diet/DietSnack.vue';

import { computed, ref, watchEffect } from 'vue';
import { useDietStore } from '@/stores/diet';

const store = useDietStore();

const today = new Date();
const year = today.getFullYear();
const month = ("0" + (1 + today.getMonth())).slice(-2);
const day = ("0" + today.getDate()).slice(-2);
const regDate = year + '-' + month + '-' + day;
const loginUserId = JSON.parse(localStorage.getItem("loginUser")).id;

const todayDietList = computed(() => store.todayDietList);

let totalCalorie = ref(0);

const calTotal = function() { 
    totalCalorie.value = 0;
    
    for(let i = 0; i < todayDietList.value.length; i++) {
        totalCalorie.value += todayDietList.value[i].calorie;
    }
}

watchEffect (async () => {
    await store.todayDietList;
    await store.getTodayDietList(loginUserId, regDate).then(calTotal)
})

</script>

<style scoped>

.diet-container {
    /* border: 1rem solid; */

    width: 80%;
    /* margin: 0.2rem auto; */

    /* margin: 20px;
    aspect-ratio: 7/4; */
    /* border: 1rem solid; */
    border-radius: 1rem;
    padding: 2rem;
    background-color: rgb(83, 120, 77);
}
.diet-title {
    font-size: 50px;
    color: white;
}
.diet-head {
    display: flex;
    justify-content: space-between;
}
.diet-box {
    display: flex;
}
.diet-calorie {
    font-size: 30px;
    padding-top: 20px;
    padding-right: 20px;
}
</style>