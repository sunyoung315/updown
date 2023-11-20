<template>
    <div class="record">
        <h2>과거 기록 조회</h2>

        <section>
            <div class="input-group flex-nowrap">
                <span class="input-group-text" id="addon-wrapping">등록일자</span>
                <input type="date" v-model="regDate" class="form-control" placeholder="Username" aria-label="Username"
                    aria-describedby="addon-wrapping">
            </div>
            &nbsp;
            <img @click="search" class="cursor" style="width: 2.5em;" src="../asset/icon/search.png" alt="검색">
        </section>

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

const weightRecord = ref({});
const exerciseRecord = ref([]);
const dietRecord = ref([]);

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

section{
    width: 300px;
    display: flex;
    margin: 20px;
    margin-left: 70px;
    margin-bottom: 50px;
}

h2{
    margin-top: 30px;
    margin-left: 110px;
}
</style>