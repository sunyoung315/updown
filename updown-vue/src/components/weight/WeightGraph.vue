<template>
    <div class="weight-graph">
        <div class="weight-head">
            <div class="weight-title">체중</div>
            <img @click="home" class="cursor" style="width: 49px; margin: 15px 5px 5px 5px;" src="../../asset/bootstrap-icon/house.svg" alt="홈으로">
        </div>
        <h4>7일간의 몸무게 변화</h4>
        <Line id="chart" :data="data" :options="options" />
    </div>
</template>

<script setup>
import { onMounted, computed } from 'vue';
import { useWeightStore } from '@/stores/weight';

import {
  Chart as ChartJS,
  CategoryScale,
  LinearScale,
  PointElement,
  LineElement,
  Title,
  Tooltip,
  Legend
} from 'chart.js'
import { Line } from 'vue-chartjs'

ChartJS.register(
  CategoryScale,
  LinearScale,
  PointElement,
  LineElement,
  Title,
  Tooltip,
  Legend
  )

const props = defineProps({
    regDates: [],
    weights: [],
})

const store = useWeightStore();

const loginUserId = JSON.parse(localStorage.getItem("loginUser")).id;

onMounted(async () => {
    await store.weihtList;
    await store.getWeightList(loginUserId);
})

const data = {
  labels: props.regDates,
  datasets: [
    {
      data: props.weights,
      borderColor: 'black', 
      fontColor: 'black',
      pointRadius: 5,
      backgroundColor: '#C16060'
    }
  ]
}

const options = {
  responsive: true,
  maintainAspectRatio: false,
  plugins: {
    legend: {
      display: false
    }
  },
}

const emit = defineEmits(["home"]);
const home = function() {
    emit("home")
}
</script>

<style scoped>
.weight-graph {
    background-color: rgb(193, 96, 96, 0.5);
    padding: 40px;
    border-radius: 20px;
}
.weight-title {
    font-size: 50px;
}
.weight-head {
    display: flex;
    justify-content: space-between;
}
h4 {
    margin-top: 15px;
    margin-left: 10px;
    margin-bottom: 15px;
}
canvas {
    margin-top: 30px;
    height: 420px !important;   
}
#chart {
    color: black !important;

}
</style>