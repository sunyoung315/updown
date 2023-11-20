<template>
    <div class="weight-detail">
        <div class="weight-head">
            <div class="weight-title">체중</div>
            <div>
                <div v-if="todayWeight && todayWeight.nowWeight">
                    <img class="cursor" @click="modify" style="width: 50px; margin: 0px 5px 0px 5px;" src="../../asset/bootstrap-icon/pencil-square.svg" alt="수정">
                    <img class="cursor" @click="graph" style="width: 43px; margin: 0px 5px 0px 5px;" src="../../asset/bootstrap-icon/graph-down-arrow.svg" alt="그래프">
                </div>
                <div v-else>
                    <img class="cursor" @click="regist" style="width: 45px;" src="../../asset/bootstrap-icon/plus-square.svg" alt="등록">
                </div>
            </div>
        </div>
        <div>
            <div v-if="todayWeight && todayWeight.nowWeight">
                <p>목표체중까지 {{ (weight - targetWeight).toFixed(1) }}kg 남았습니다.</p>
                <h4>{{ weight }}kg</h4>
            </div>
            <div v-else>
                <h4>0.0kg</h4>
            </div>
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
.weight-detail {
    background-color: rgb(193, 96, 96);
    padding: 2em;
    border-radius: 20px;
}
.weight-title {
    font-size: 40px;
    color: white;
}
.weight-head {
    display: flex;
    justify-content: space-between;
}
</style>