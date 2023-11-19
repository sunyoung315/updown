<template>
    <div>
        <h2>체중</h2>
        <div v-if="todayWeight && todayWeight.nowWeight">
            <img class="cursor" @click="modify" style="width: 3em;" src="../../asset/icon/edit.png" alt="수정">
            <img class="cursor" @click="graph" style="width: 3em;" src="../../asset/icon/graph.png" alt="그래프">
            <p>목표체중까지 {{ (weight - targetWeight).toFixed(1) }}kg 남았습니다.</p>
            <h4>{{ weight }}kg</h4>
        </div>
        <div v-else>
            <img class="cursor" @click="regist" style="width: 3em;" src="../../asset/icon/add.png" alt="등록">
            <h4>0.0kg</h4>
        </div>
    </div>
</template>

<script setup>
import { computed } from 'vue';
import { useWeightStore } from '@/stores/weight';

const store = useWeightStore();

const todayWeight = computed(() => store.todayWeight);
const weight = computed(() => store.todayWeight.nowWeight);

const targetWeight = JSON.parse(localStorage.getItem("loginUser")).targetWeight;

const emits = defineEmits(["modify", "regist", "graph"]);

const modify = function() {
    emits("modify");
}

const regist = function() {
    emits("regist");
}

const graph = function() {
    emits("graph");
}

</script>

<style scoped>

</style>