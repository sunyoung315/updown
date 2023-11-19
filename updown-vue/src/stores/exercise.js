import { ref, onMounted } from 'vue'
import { defineStore } from 'pinia';
import axios from 'axios';

const REST_EXERCISE_API = `http://localhost:8080/updown/exercise`;

export const useExerciseStore = defineStore('exercise', () => {
  const today = new Date();
  const year = today.getFullYear();
  const month = ("0" + (1 + today.getMonth())).slice(-2);
  const day = ("0" + today.getDate()).slice(-2);
  const regDate = `${year}-${month}-${day}`;

  const loginUserId = JSON.parse(localStorage.getItem("loginUser")).id;

  const todayExerciseList = ref([]);

  const getExerciseList = async function(loginUserId, regDate) {
    await axios.get(REST_EXERCISE_API, {
      params: {
        loginUserId: loginUserId,
        regDate: regDate,
      }
    })
    .then((res) => {
      todayExerciseList.value = res.data;
    })
  }
      
  let todayTime = ref(0);
  let todayCalorie = ref(0);

  onMounted(async () => {
    await getExerciseList(loginUserId, regDate);
    for(let i = 0; i < todayExerciseList.value.length; i++) {
        todayTime.value += todayExerciseList.value[i].time;
        todayCalorie.value += todayExerciseList.value[i].calorie;
    }
  })

  const modifyExercise = async function(newExercise) {
    await axios({
      url:`${REST_EXERCISE_API}/modify`,
      method: 'PUT',
      data: newExercise,
      headers: {
        "Content-Type": "application/json"
      },
    })
    .then(() => {
      getExerciseList(loginUserId, regDate)
    })
  }

  const uploadExercise = async function(newExercise) {
    await axios({
      url: `${REST_EXERCISE_API}/upload`,
      method: 'POST',
      data: newExercise,
      headers: {
        "Content-Type": "application/json"
      },
    })
    .then(() => {
      getExerciseList(loginUserId, regDate),
      exerciseInfoList.value = []
    })
  }

  const exerciseInfoList = ref([]);

  const searchExerciseInfo = async function(word) {
    await axios({
      url: `${REST_EXERCISE_API}/search/${word}`,
      method: 'GET',
    })
    .then((res) => {
      word = '',
      exerciseInfoList.value = res.data
    })
  }

  const exerciseInfo = ref({});

  const searchExerciseInfoDetail = async function(type) {
    await axios({
      url: `${REST_EXERCISE_API}/searchDetail/${type}`,
      method: 'GET',
    })
    .then((res) => {
      exerciseInfo.value = res.data
    })
  }

  return { todayExerciseList, getExerciseList, modifyExercise, uploadExercise, exerciseInfoList, searchExerciseInfo, todayTime, todayCalorie, exerciseInfo, searchExerciseInfoDetail }
})