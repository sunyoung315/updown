<template>
    <div class="record">
        <div class="record-title">과거 기록 조회</div>
        <div class="calendar">
            <VCalendar expanded :attributes='attrs' @dayclick="showRecord" v-model="date" view="weekly"></VCalendar>
        </div>
        <RecordWeight :weightRecord="weightRecord" class="record-view"/>
        <RecordExercise :exerciseRecord="exerciseRecord" class="record-view"/>
        <RecordDiet :dietRecord="dietRecord" class="record-view"/>
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

const attrs = ref([
    {
        highlight: {
            color: 'blue',
            fillMode: 'light',
        },
    },
]);

const weightRecord = ref({});
const exerciseRecord = ref([]);
const dietRecord = ref([]);

const showRecord = (day) => {
    regDate.value = day.id;
    search();
}

const search = function () {
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
.record {
    display: flex;
    flex-direction: column;
    justify-content: space-around;
    width: 1270px;
    margin-top: 50px;
}

.record-view {
    margin-top: 50px;
    margin-bottom: 50px;
    margin-left: 80px;
}

section {
    width: 300px;
    display: flex;
    margin: 20px;
    margin-left: 70px;
    margin-bottom: 50px;
}

.record-title {
    font-size: 40px;
    margin-top: 20px;
    margin-left: 110px;
    margin-bottom: 20px;
}

.calendar{
    width: 1000px;
    margin-left: 70px;
    margin-bottom: 20px;
}
</style>