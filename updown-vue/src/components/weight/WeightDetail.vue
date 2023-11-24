<template>
    <div class="weight-detail">
        <div class="weight-head">
            <div class="weight-title">체중</div>
            <div>
                <div v-if="todayWeight && todayWeight.nowWeight">
                    <img class="cursor" id="modify-icon" @click="modify" src="../../asset/bootstrap-icon/pencil-square.svg" alt="수정">
                    <img class="cursor" @click="graph" src="../../asset/bootstrap-icon/graph-down-arrow.svg" alt="그래프">
                </div>
                <div v-else>
                    <img class="cursor" @click="regist" src="../../asset/bootstrap-icon/plus-square.svg" alt="등록">
                </div>
            </div>
        </div>
        <div>
            <div class="weight-main" v-if="todayWeight && todayWeight.nowWeight">
                <div class="weight-scale">
                    <p class="weight-scale-number">{{ weight }}kg</p>
                </div>
                <p class="weight-target">
                    목표 체중까지 <span class="weight-value">{{ (targetWeight - weight).toFixed(1) }}kg</span> 남았습니다.
                </p>
            </div>
            <div class="weight-main" v-else>
                <div class="weight-scale">
                    <p class="weight-scale-number">0.0kg</p>
                </div>
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
    padding: 2rem;
    border-radius: 1rem;
    margin: 1rem;
}
.weight-title {
    font-size: xx-large;
    font-weight: bolder;
    color: white;
}
.weight-head {
    display: flex;
    justify-content: space-between;
}
.weight-main {
    text-align: center;
}
.weight-target {
    font-size: medium;
    font-weight: 510;
    text-align: center;
}
.weight-scale {
    display: inline-block;
    font-size: 2.5rem;
    font-weight: bolder;
    background-image: url('../../asset/image/weight-scale.png');
    background-repeat: no-repeat;
    background-size: 17rem; 
    width: 17rem;
    height: 17rem;
    margin-top: 1.5rem;
    margin-bottom: 0.5rem;
}
.weight-scale-number {
    text-align: center;
    margin-bottom: 0;
    line-height: 23rem;
    color: white;
}
.weight-value {
    color: white;
    font-weight: 550;
}

.cursor {
    width: 2rem;
    margin: 0.5rem 0 0.25rem 1rem;
}
#modify-icon {
    width: 2.2rem;
}
</style>