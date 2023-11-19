<template>
    <div>
        <h2>과거 기록 조회</h2>
        <span>등록일자 : </span>
        <input type="date" v-model="regDate">
        <img @click="search" class="cursor" style="width: 2.5em;" src="../asset/icon/search.png" alt="검색">
        <RecordWeight :weightRecord="weightRecord"/>
        <RecordExercise :exerciseRecord="exerciseRecord"/>
        <RecordDiet :dietRecord="dietRecord"/>
    </div>
</template>

<script setup>
import { ref } from 'vue';

import RecordWeight from '@/components/record/RecordWeight.vue'
import RecordExercise from '@/components/record/RecordExercise.vue'
import RecordDiet from '@/components/record/RecordDiet.vue'
import axios from 'axios';

const regDate = ref('');

const loginUserId = JSON.parse(localStorage.getItem("loginUser")).id;

const REST_UPDOWN_API = `http://localhost:8080/updown`;

const weightRecord = ref({});
const exerciseRecord = ref([]);
const dietRecord = ref([]);

const search = function() {
    axios({
        url: `${REST_UPDOWN_API}/weight`,
        method: 'GET',
        params: {
            loginUserId: loginUserId,
            regDate: regDate.value,
        }
    })
    .then((res) => {
        weightRecord.value = res.data
    })
    
    axios({
        url: `${REST_UPDOWN_API}/exercise`,
        method: 'GET',
        params: {
            loginUserId: loginUserId,
            regDate: regDate.value,
        }
    })
    .then((res) => {
        exerciseRecord.value = res.data
    })
    
    axios({
        url: `${REST_UPDOWN_API}/diet`,
        method: 'GET',
        params: {
            loginUserId: loginUserId,
            regDate: regDate.value,
        }
    })
    .then((res) => {
        dietRecord.value = res.data
    })
}

</script>

<style scoped>

</style>