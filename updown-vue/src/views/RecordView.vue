<template>
    <div class="record">
        <div class="record-title">과거 기록 조회</div>
        <div class="calendar">
            <VCalendar expanded :attributes='attrs' @dayclick="showRecord" view="weekly"></VCalendar>
        </div>
        <RecordWeight :weightRecord="weightRecord" class="record-view" />
        <RecordExercise :exerciseRecord="exerciseRecord" class="record-view" />
        <RecordDiet :dietRecord="dietRecord" class="record-view" />
    </div>
</template>

<script setup>
import { ref, onMounted } from 'vue';

import RecordWeight from '@/components/record/RecordWeight.vue'
import RecordExercise from '@/components/record/RecordExercise.vue'
import RecordDiet from '@/components/record/RecordDiet.vue'
import axios from 'axios';

const regDate = ref('');

const loginUserId = JSON.parse(localStorage.getItem("loginUser")).id;

const REST_UPDOWN_API = `http://localhost:8080/updown`;

const attrs = ref([
    {
        key: 'today',
        highlight: {
            color: 'blue',
            fillMode: 'light',
        },
        dates: new Date(),
    },
]);

const today = new Date();
const year = today.getFullYear();
const month = ("0" + (1 + today.getMonth())).slice(-2);
const day = ("0" + today.getDate()).slice(-2);
regDate.value = year + '-' + month + '-' + day;


onMounted(() => {
    regDate.value = regDate.value
    search();
})

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
    margin-top: 20px;
    margin-left: 20px;
    padding-top: 45px;
    padding-bottom: 100px;
    background-color: white;
    border-radius: 20px;
    border: 1px solid rgb(199, 199, 199);
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

.calendar {
    width: 1000px;
    margin-left: 70px;
    margin-bottom: 20px;
}
</style>