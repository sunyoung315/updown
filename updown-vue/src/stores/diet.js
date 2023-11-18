import { ref } from 'vue'
import { defineStore, storeToRefs } from 'pinia';
import router from '@/router';
import axios from 'axios';

const REST_WEIGHT_API = `http://localhost:8080/updown/diet`;

export const useDietStore = defineStore('diet', () => {

    const today = new Date();

    const loginUserId = JSON.parse(localStorage.getItem("loginUser")).id;

    // 오늘 기록한 식단
    const todayDietBreakFast = ref({});
    const todayDietLunch = ref({});
    const todayDietDinner = ref({});
    const todayDietSnack = ref({});

    const getDietBreakFast = async function(loginUserId, regDate) {
        await axios.get(`${REST_WEIGHT_API}/breakfast`, {
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
    return {  }
})
