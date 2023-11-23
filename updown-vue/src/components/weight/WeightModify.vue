<template>
    <div class="weight-modify">
        <div class="weight-head">
            <div class="weight-title">체중</div>
            <div>
                <img @click="modify" class="cursor" src="../../asset/bootstrap-icon/save.svg" alt="저장">
                <img @click="home"  id="home-icon" class="cursor" src="../../asset/bootstrap-icon/house.svg" alt="홈으로">
            </div>
        </div>
        <div class="today-weight-box">
            <p class="today-weight">오늘의 체중은?</p>
            <div class="weight-value">
                &nbsp;<input type="number" :placeholder=weight v-model="newWeight.nowWeight"><span class="weight-unit">kg</span>
                <p id="check" v-if="!newWeight.nowWeight">체중을 등록해주세요.</p>
            </div>
        </div>    
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
    if(newWeight.value.nowWeight<=0 || !newWeight.value.nowWeight) return;
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
.weight-modify {
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
.today-weight {
    font-size: larger;
    text-align: center;
    font-weight: bold;
    margin: 0;
    margin-bottom: 0.2rem;
}
input {
    width: 12rem;
    height: 6rem;
    font-size: 4rem;
}
.weight-unit {
    font-size: 4rem;
}
.weight-value {
    text-align: center;
}
.today-weight-box {
    position: relative;
    top: 6rem;
}
#check{
    font-size: small;
    color: rgb(169, 65, 65);
}
.cursor {
    width: 2rem;
    margin: 0.5rem 0 0.25rem 1rem;
}
#home-icon {
    width: 2.25rem;
    margin: 0.25rem 0 0 1rem;
}
</style>