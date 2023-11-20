<template>
    <div class="diet-container ratio ratio-1x1">
        <h4>식단</h4> 
        <div class="diet-box">
            <DietBreakFast />
            <DietLunch />
            <DietDinner />
            <DietSnack />
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
    display: inline;
    width: 100%;
    margin: 1em;
    padding: 1em;
    background-color: rgb(83, 120, 77);
    border-radius: 20px;
}
.diet-box {
    display: flex;
    position: relative;
}
</style>