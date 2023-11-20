<template>
    <div class="container">
        <h4>식단</h4> 
        <div class="diet-box">
            <DietBreakFast @caloriesum="caloriesum" class="diet" />
            <DietLunch class="diet" />
        </div>
        <div class="diet-box">
            <DietDinner class="diet" />
            <DietSnack class="diet" />
        </div>
    </div>
</template>


<script setup>
import DietBreakFast from '@/components/diet/DietBreakFast.vue';
import DietLunch from '@/components/diet/DietLunch.vue';
import DietDinner from '@/components/diet/DietDinner.vue';
import DietSnack from '@/components/diet/DietSnack.vue';

</script>

<style scoped>
.container {
    border: 1px solid black;
}

.diet-box {
    display: flex;
}

.diet {
    display: inline;
    width: 50%;
}
</style>