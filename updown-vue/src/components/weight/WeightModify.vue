<template>
    <div>
        <h2>체중</h2>
        <img @click="modify" @save="save" class="cursor" style="width: 2.5em;" src="../../asset/icon/save.png" alt="저장">
        <h4>오늘의 체중은?</h4>
        <input type="number" v-model="store.todayWeight.nowWeight" :placeholder=weight>kg
    </div>
</template>

<script setup>
import { useWeightStore } from '@/stores/weight';

const store = useWeightStore();

const weight = store.todayWeight.nowWeight;

const modify = function() {
    store.modifyWeight();
}

</script>

<style scoped>

</style>