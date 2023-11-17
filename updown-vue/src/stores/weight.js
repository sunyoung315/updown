import { ref } from 'vue'
import { defineStore } from 'pinia';
import router from '@/router';
import axios from 'axios';

const REST_WEIGHT_API = `http://localhost:8080/updown/weight`;

export const useWeightStore = defineStore('weight', () => {

    const todayWeight = ref({});

    const getWeight = function(loginUserId, regDate) {
        axios.get(REST_WEIGHT_API, {
            params: {
                loginUserId: loginUserId,
                regDate: regDate,
            }
        })
        .then((res) => {
            todayWeight.value = res.data;
        })
    }

    const modifyWeight = function() {
        console.log(todayWeight.value)
        axios({
            url: `${REST_WEIGHT_API}/modify`,
            method: 'PUT',
            data: todayWeight.value,
            headers: {
                "Content-Type": "application/json"
            },
        })
        .catch((err) => {
            console.log('오류 : ' + err)
        })
    }

    return { todayWeight, getWeight, modifyWeight }
})
