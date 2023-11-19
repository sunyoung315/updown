<template>
    <div class="container">
        <KeepAlive>
            <component :is="choose" @getInfo="getInfo" :info="info" @regist="regist" @modify="modify" :exercise="exercise" @remove="remove" @home="home" @list="list" @search="search" :time="time" :calorie="calorie"></component>
        </KeepAlive>
    </div>
</template>

<script setup>
import { shallowRef, ref, onMounted, computed } from 'vue';
import { useExerciseStore } from '@/stores/exercise';

import ExerciseDetail from '@/components/exercise/ExerciseDetail.vue';
import ExerciseModify from '@/components/exercise/ExerciseModify.vue';
import ExerciseUpload from '@/components/exercise/ExerciseUpload.vue';
import ExerciseList from '@/components/exercise/ExerciseList.vue';
import ExerciseSearch from '@/components/exercise/ExerciseSearch.vue';
import axios from 'axios';

let choose = shallowRef(ExerciseDetail);

let exercise = ref({});

let info = ref({});

const getInfo = function(i) {
    choose.value = ExerciseUpload;
    info.value = i;
}

const modify = function(e) {
    choose.value = ExerciseModify;
    exercise.value = e;
}

const regist = function() {
    info.value = {};
    choose.value = ExerciseUpload;
}

const home = function() {
    choose.value = ExerciseDetail;
}

const list = function() {
    choose.value = ExerciseList;
}

const search = function() {
    choose.value = ExerciseSearch
}

const REST_EXERCISE_API = `http://localhost:8080/updown/exercise`;
const remove = async function(exercise) {
    await axios({
        url: `${REST_EXERCISE_API}/remove/${exercise.no}`,
        method: 'DELETE',
    })
    .then(() => {
        store.getExerciseList(loginUserId, regDate)
    })
}

const today = new Date();
const year = today.getFullYear();
const month = ("0" + (1 + today.getMonth())).slice(-2);
const day = ("0" + today.getDate()).slice(-2);
const regDate = `${year}-${month}-${day}`;

const loginUserId = JSON.parse(localStorage.getItem("loginUser")).id;

const store = useExerciseStore();

onMounted(async () => {
    await store.getExerciseList(loginUserId, regDate);
})

const todayExerciseList = computed(() => store.todayExerciseList);

let time = ref(0);
let calorie = ref(0);

for(let i = 0; i < todayExerciseList.value.length; i++) {
    time += todayExerciseList.value[i].time;
    calorie += todayExerciseList.value[i].calorie;
}

</script>

<style scoped>
.container {
    border: 1px solid black;
}

</style>