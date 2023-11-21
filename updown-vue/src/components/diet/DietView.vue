<template>
    <div class="diet-container">
        <div class="diet-head">
            <div class="diet-title">식단</div>
            <!-- <div>총 섭취 칼로리 : </div> -->
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

</script>

<style scoped>
.diet-container {
    margin: 20px;
    padding: 32px;
    background-color: rgb(83, 120, 77);
    border-radius: 20px;
    aspect-ratio: 7/4   ;
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
</style>