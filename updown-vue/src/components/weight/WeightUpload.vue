<template>
    <div class="weight-upload">
        <div class="weight-head">
            <div class="weight-title">체중</div>
            <img @click="upload" class="cursor" style="width: 45px; height: 45px;" src="../../asset/icon/save.png" alt="저장">
        </div>
        <h4>오늘의 체중은?</h4>
        <input type="number" v-model="newWeight.nowWeight">kg
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
    nowWeight: 0.0,
    userId: JSON.parse(localStorage.getItem("loginUser")).id,
});

const emits = defineEmits(["home",])

const store = useWeightStore();

onMounted(async () =>{
    await getTodayWeight();
})

const getTodayWeight = async function() {
    await store.getWeight(loginUserId, regDate);
}

const upload = async function() {
    await store.uploadWeight(newWeight.value);
    await getTodayWeight();
    emits("home");
}

</script>

<style scoped>
.weight-upload {
    background-color: rgb(193, 96, 96, 0.5);
    padding: 2em;
    border-radius: 20px;
}
.weight-title {
    font-size: 40px;
}
.weight-head {
    display: flex;
    justify-content: space-between;
}
</style>