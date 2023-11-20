<template>
    <div class="today-container">
        <div class="today-box">
            <WeightView />
            <ExerciseView />
        </div>
        <div class="today-box">
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
.today-container {
    width: 70%;
}
.today-box {
    display: flex;
}
</style>