<template>
    <div class="weight-graph">
        <div class="weight-head">
            <div class="weight-title">체중</div>
            <img @click="home" class="cursor" style="width: 49px; margin: 15px 5px 5px 5px;" src="../../asset/bootstrap-icon/house.svg" alt="홈으로">
        </div>
        <h4 style="padding-left: 10px;">7일간의 몸무게 변화</h4>
        <div id="chart">
            <apexchart type="line" height="450" :options="chartOptions" :series="series"></apexchart>
        </div>
    </div>
</template>

<script setup>
import { onMounted, computed, ref } from 'vue';
import { useWeightStore } from '@/stores/weight';

const store = useWeightStore();

const loginUserId = JSON.parse(localStorage.getItem("loginUser")).id;

const regDates = computed(() => store.weightList.map((w) => w.regDate));
const weights = computed(() => store.weightList.map((w) => w.nowWeight));

onMounted(async () => {
  await store.weightList;
  await store.getWeightList(loginUserId);
})

const series = ref([
  {
    name: "Weight",
    data: weights,
  }
]);

const chartOptions = ref({
  colors: ["#C16060"],
  chart: {
    type: 'line',
    height: 450,
    zoom: {
      enabled: false
    },
  },
  dataLabels: {
    enabled: false,
    style: {
      fontFamily: 'mobiletitle'
    }
  },
  stroke: {
    curve: 'straight',
    width: 5,
    colors: ["#C16060"]
  },
  markers: {
    size: 5,
    colors: ["#C16060"]
  },
  xaxis: {
    categories: regDates,
    labels: {
      style: {
        fontSize: 15,
        fontFamily: 'mobiletitle',
      }
    }
  },
  yaxis: [
    {
      axisTicks: {
        show: true,
      },
      axisBorder: {
        show: true,
      },
      labels: {
        style: {
          fontSize: 15,
          fontFamily: 'mobiletitle',
        },
      }
    }
  ],
  tooltip: {
    style: {
      fontFamily: 'mobiletitle',
    },
  }
});

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
</style>