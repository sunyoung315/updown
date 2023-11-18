<template>
    <div>
        <h2>체중</h2>
        <img @click="upload" class="cursor" style="width: 2.5em;" src="../../asset/icon/save.png" alt="저장">
        <h4>오늘의 체중은?</h4>
        <input type="number" v-model="newWeight.nowWeight" placeholder=0>kg
    </div>
</template>

<script setup>
import { ref, onMounted } from 'vue';
import { useWeightStore } from '@/stores/weight';

const today = new Date();
const year = today.getFullYear();
const month = ("0" + (1 + today.getMonth())).slice(-2);
const day = ("0" + today.getDate()).slice(-2);
const regDate = `${year}-${month}-${day}`;

const loginUserId = JSON.parse(localStorage.getItem("loginUser")).id;

const newWeight = ref({
    nowWeight: 0,
    userId: JSON.parse(localStorage.getItem("loginUser")).id,
});

const emits = defineEmits(["home",])

const store = useWeightStore();

onMounted(() =>{
    getTodayWeight();
})

const getTodayWeight = async function() {
    await store.getWeight(loginUserId, regDate);
}

const upload = function() {
    store.uploadWeight(newWeight.value);
    getTodayWeight();
    emits("home");
}

</script>

<style scoped>

</style>