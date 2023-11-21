<template>
    <div class="weight-detail">
        <div class="weight-head">
            <div class="weight-title">체중</div>
            <div>
                <div v-if="todayWeight && todayWeight.nowWeight">
                    <img class="cursor" @click="modify" style="width: 50px; margin: 15px 5px 0px 5px;" src="../../asset/bootstrap-icon/pencil-square.svg" alt="수정">
                    <img class="cursor" @click="graph" style="width: 43px; margin: 15px 5px 0px 5px;" src="../../asset/bootstrap-icon/graph-down-arrow.svg" alt="그래프">
                </div>
                <div v-else>
                    <img class="cursor" @click="regist" style="width: 45px; margin: 15px 5px 0px 5px;" src="../../asset/bootstrap-icon/plus-square.svg" alt="등록">
                </div>
            </div>
        </div>
        <div>
            <div class="weight-main" v-if="todayWeight && todayWeight.nowWeight">
                <div class="weight-scale">
                    <p class="weight-scale-number">{{ weight }}kg</p>
                </div>
                <p class="weight-target">
                    목표체중까지 <span class="weight-value">{{ (weight - targetWeight).toFixed(1) }}kg</span> 남았습니다.
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
    padding: 40px;
    border-radius: 20px;
}
.weight-title {
    font-size: 50px;
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
    line-height: 70px;
    font-size: 25px;
    text-align: center;
}
.weight-scale {
    display: inline-block;
    font-size: 60px;
    background-image: url('../../asset/image/weight-scale.png');
    background-repeat: no-repeat;
    background-size: 400px 400px;
    width: 400px;
    height: 400px;
    margin-top: 30px;
    margin-bottom: 0px;
}
.weight-scale-number {
    text-align: center;
    margin-bottom: 0px;
    line-height: 550px;
    color: white;
}
.weight-value {
    color: white;
}
</style>