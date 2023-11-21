<template>
    <div class="weight-upload">
        <div class="weight-head">
            <div class="weight-title">체중</div>
            <div>
                <img @click="home" class="cursor" style="width: 49px; margin: 13px 5px 0px 5px;" src="../../asset/bootstrap-icon/house.svg" alt="홈으로">
                <img @click="upload" class="cursor" style="width: 43px; margin: 13px 5px 0px 5px;" src="../../asset/bootstrap-icon/save.svg" alt="저장">
            </div>
        </div>
        <div class="today-weight-box">
            <p class="today-weight">오늘의 체중은?</p>
            <div class="weight-value">
                <input type="number" v-model="newWeight.nowWeight"><span class="weight-unit">kg</span>
                <p id="check" v-if="!newWeight.nowWeight">체중을 등록해주세요.</p>
            </div>
        </div>
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

const home = function() {
    emits("home");
}

const upload = async function() {
    if(newWeight.value.nowWeight<=0 || !newWeight.value.nowWeight) return;
    await store.uploadWeight(newWeight.value);
    await getTodayWeight();
    emits("home");
}

</script>

<style scoped>
.weight-upload {
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
.today-weight {
    font-size: 30px;
    text-align: center;
}
input {
    width: 220px;
    height: 120px;
    font-size: 75px;
}
.weight-unit {
    font-size: 75px;
}
.weight-value {
    text-align: center;
}
.today-weight-box {
    position: relative;
    top: 140px
}
#check{
    font-size: 12.3px;
    color: red;
}
</style>