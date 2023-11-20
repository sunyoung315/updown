<template>
    <div class="record">
        <h2>과거 기록 조회</h2>
        <br>
        <div class="calendar">
            <VCalendar expanded :attributes='attrs' @dayclick="showRecord" v-model="date" view="weekly"></VCalendar>
        </div>
        <br>
        <a>
            <RecordWeight :weightRecord="weightRecord" />
        </a>
        <a>
            <RecordExercise :exerciseRecord="exerciseRecord" />
        </a>
        <a>
            <RecordDiet :dietRecord="dietRecord" />
        </a>
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
        key: 'today',
        highlight: {
            color: 'blue',
            fillMode: 'light',
        },
        dates: new Date(),
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
    /* align-items: center; */
    width: 100%;
}

a {
    margin: 20px;
    margin-left: 70px;
}

section {
    width: 300px;
    display: flex;
    margin: 20px;
    margin-left: 70px;
    margin-bottom: 50px;
}

h2 {
    margin-top: 20px;
    margin-left: 110px;
}

.calendar{
    width: 1000px;
    margin-left:70px;
}
</style>