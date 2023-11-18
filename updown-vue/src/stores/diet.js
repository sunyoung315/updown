import { ref } from 'vue'
import { defineStore, storeToRefs } from 'pinia';
import router from '@/router';
import axios from 'axios';

const REST_DIET_API = `http://localhost:8080/updown/diet`;

export const useDietStore = defineStore('diet', () => {

    const today = new Date();
    const year = today.getFullYear();
    const month = ("0" + (1 + today.getMonth())).slice(-2);
    const day = ("0" + today.getDate()).slice(-2);
    const regDate = year + '-' + month + '-' + day;
    const loginUserId = JSON.parse(localStorage.getItem("loginUser")).id;

    // 오늘 기록한 식단
    const newDietBreakFast = ref({
        diet: '',
        kcal: '',
        img: '',
        orgImg: ''
    });
    const newDietLunch = ref({
        diet: '',
        kcal: '',
        img: '',
        orgImg: ''
    });
    const newDietDinner = ref({
        diet: '',
        kcal: '',
        img: '',
        orgImg: ''
    });
    const newDietSnack = ref({
        diet: '',
        kcal: '',
        img: '',
        orgImg: ''
    });

    const getDietBreakFast = async function(loginUserId, regDate) {
        await axios.get(REST_DIET_API, {            
            params: {
                loginUserId: loginUserId,
                regDate: regDate,
            },
            headers:{
                "Content-Type": "application/x-www-form-urlencode"
            }
        })
        // .then((res) => {
        //     console.log(res.config.params.loginUserId)
        //     console.log(res.config.params.regDate)
        // })
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
    
    //일단 아침을 기준으로 업로드
    const uploadDiet = async function(newDiet) {
        console.log(newDiet)
        await axios({
            url: `${REST_DIET_API}/upload`,
            method: 'POST',
            data: newDiet,
            headers: {
                "Content-Type": "application/json"
            },
        })
        .then(
            getDietBreakFast(loginUserId, regDate),
            router.push({name: 'today'})
        )
        .catch((err) => {
            console.log('오류 : ' + err)
        })
    }
    return { today, newDietBreakFast, newDietLunch, newDietDinner, newDietSnack,  getDietBreakFast, uploadDiet }
})
