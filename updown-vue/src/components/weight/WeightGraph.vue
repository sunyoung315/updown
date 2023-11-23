<template>
    <div class="weight-graph">
        <div class="weight-head">
            <div class="weight-title">체중</div>
            <img @click="home" class="cursor" src="../../asset/bootstrap-icon/house.svg" alt="홈으로">
        </div>
        <p>7일간의 몸무게 변화</p>
        <div id="chart">
            <apexchart type="line" height="290" :options="chartOptions" :series="series"></apexchart>
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
    height: 290,
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
        fontSize: 11,
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
          fontSize: 11,
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
p {
  font-size: medium;
  font-weight: bold;
  padding-top: 0.5rem;
  padding-left: 2rem;
  margin-bottom: 0;
}
canvas {
  margin-top: 0.5rem;
}
.cursor {
  width: 2.25rem;
  margin: 0.25rem 0 0 1rem;
}
</style>