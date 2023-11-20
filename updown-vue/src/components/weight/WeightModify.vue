<template>
    <div class="weight-modify">
        <div class="weight-head">
            <div class="weight-title">체중</div>
            <div>
                <img @click="modify" class="cursor" style="width: 43px; margin: 0px 5px 0px 5px;" src="../../asset/bootstrap-icon/save.svg" alt="저장">
                <img @click="home" class="cursor" style="width: 49px; margin: 0px 5px 0px 5px;" src="../../asset/bootstrap-icon/house.svg" alt="홈으로">
            </div>
        </div>
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
.weight-modify {
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