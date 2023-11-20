<template>
    <div class="diet-container">
        <h2>식단</h2> 
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

</script>

<style scoped>
.diet-container {
    margin: 20px;
    padding: 32px;
    background-color: rgb(83, 120, 77);
    border-radius: 20px;

    aspect-ratio: 1/1;
}
.diet-box {
    display: flex;
}
</style>