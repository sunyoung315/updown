<template>
    <div class="today-container">
        <div class="today-box">
            <WeightView class="weight"/>
            <ExerciseView class="exercise"/>
        </div>
        <DietView/>
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
.weight {
    display: inline;
    width: 50%;
}
.exercise {
    display: inline;
    width: 50%;
}
</style>