<template>
    <div>
        <h2>체중</h2>
        <img @click="modify" class="cursor" style="width: 2.5em;" src="../../asset/icon/save.png" alt="저장">
        <img @click="home" class="cursor" style="width: 2.5em;" src="../../asset/icon/home.png" alt="홈으로">
        <h4>오늘의 체중은?</h4>
        <input type="number" :placeholder=weight v-model="newWeight.nowWeight">kg
    </div>
</template>

<script setup>
import { ref, onMounted, computed } from 'vue';
import { useWeightStore } from '@/stores/weight';

const today = new Date();
const year = today.getFullYear();
const month = ("0" + (1 + today.getMonth())).slice(-2);
const day = ("0" + today.getDate()).slice(-2);
const regDate = `${year}-${month}-${day}`;

const loginUserId = JSON.parse(localStorage.getItem("loginUser")).id;

const store = useWeightStore();

const newWeight = ref({
    nowWeight: 0,
    regDate: regDate,
    userId: loginUserId,
});

const emits = defineEmits(["home"])


const weight = computed(() => store.todayWeight.nowWeight);

onMounted(async () =>{
    await getTodayWeight();
    newWeight.value.nowWeight = store.todayWeight.nowWeight;
})

const getTodayWeight = async function() {
    await store.getWeight(loginUserId, regDate);
}

const modify = async function() {
    await store.modifyWeight(newWeight.value);
    await getTodayWeight();
    emits("home");
}

const home = function() {
    emits("home");
    newWeight.value.nowWeight = weight.value;
}

</script>

<style scoped>

</style>