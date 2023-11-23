<template>
    <div class="today-container">
        <div class="today-box">
            <WeightView />
            <ExerciseView />
        </div>
        <div>
            <DietView class="diet"/>
        </div>
    </div>
</template>

<script setup>
import DietView from '@/components/diet/DietView.vue';
import ExerciseView from '@/components/exercise/ExerciseView.vue';
import WeightView from '@/components/weight/WeightView.vue';



</script>

<style scoped>
    @import url('https://fonts.googleapis.com/css2?family=Black+Han+Sans&family=Gamja+Flower&family=IBM+Plex+Sans:ital,wght@0,100;0,200;0,300;0,400;0,500;0,600;0,700;1,100;1,200;1,300;1,400;1,500;1,600;1,700&display=swap');
    .today-container {
        width: 1270px;
    }
    .today-box {
        display: flex;
    }
</style>