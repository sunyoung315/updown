import { ref } from 'vue'
import { defineStore } from 'pinia';
import router from '@/router';
import axios from 'axios';

const REST_WEIGHT_API = `http://localhost:8080/updown/weight`;

export const useWeightStore = defineStore('weight', () => {

    const today = new Date();
    const year = today.getFullYear();
    const month = ("0" + (1 + today.getMonth())).slice(-2);
    const day = ("0" + today.getDate()).slice(-2);
    const regDate = `${year}-${month}-${day}`;

    const loginUserId = JSON.parse(localStorage.getItem("loginUser")).id;

    const todayWeight = ref({});

    const weightList = ref([]);

    const getWeightList = async function(loginUserId) {
        await axios({
            url: `${REST_WEIGHT_API}/list`,
            method: 'GET',
            params: {
                loginUserId: loginUserId,
            }
        })
        .then((res) => {
            weightList.value = res.data;
        })
        .catch((err) => {
            console.log(err);
        })
    }

    const getWeight = async function(loginUserId, regDate) {
        await axios.get(REST_WEIGHT_API, {
            params: {
                loginUserId: loginUserId,
                regDate: regDate,
            }
        })
        .then((res) => {
            todayWeight.value = res.data;
        })
    }

    const modifyWeight = async function(newWeight) {
        await axios({
            url: `${REST_WEIGHT_API}/modify`,
            method: 'PUT',
            data: newWeight,
            headers: {
                "Content-Type": "application/json"
            },
        })
        .then(
            getWeight(loginUserId, regDate),
            router.push({name: 'today'})
        )
        .catch((err) => {
            console.log('오류 : ' + err)
        })
    }
    
    const uploadWeight = async function(newWeight) {
        await axios({
            url: `${REST_WEIGHT_API}/upload`,
            method: 'POST',
            data: newWeight,
            headers: {
                "Content-Type": "application/json"
            },
        })
        .then(
            getWeight(loginUserId, regDate),
            router.push({name: 'today'})
        )
        .catch((err) => {
            console.log('오류 : ' + err)
        })
    }
    return { todayWeight, getWeight, modifyWeight, uploadWeight, getWeightList, weightList }
})
