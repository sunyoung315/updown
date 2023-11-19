import { ref } from 'vue'
import { defineStore, storeToRefs } from 'pinia';
import { useRouter } from 'vue-router';
import axios from 'axios';

const REST_DIET_API = `http://localhost:8080/updown/diet`;

export const useDietStore = defineStore('diet', () => {

    const today = new Date();
    const year = today.getFullYear();
    const month = ("0" + (1 + today.getMonth())).slice(-2);
    const day = ("0" + today.getDate()).slice(-2);
    const regDate = year + '-' + month + '-' + day;
    const loginUserId = JSON.parse(localStorage.getItem("loginUser")).id;

    const router = useRouter();

    // 등록할 식단
    const newDiet = ref({
        category: '',
        food: '',
        calorie: '',
        img: '',
        orgImg: '',
        userId: loginUserId
    });

    const breakfastimg = ref('');
    const lunchimg = ref('');
    const dinnerimg = ref('');
    const snackimg = ref('');

    // 오늘 기록한 식단
    const todayDietBreakFast = ref([]);
    const todayDietLunch = ref([]);
    const todayDietDinner = ref([]);
    const todayDietSnack = ref([]);


    // 아침 식단 기록 조회
    const getDietBreakFast = async function (loginUserId, regDate) {
        await axios.get(`${REST_DIET_API}/breakfast`, {
            params: {
                loginUserId: loginUserId,
                regDate: regDate,
            },
            headers: {
                "Content-Type": "application/x-www-form-urlencode"
            }
        })
            .then((res) => {
                todayDietBreakFast.value = Array.isArray(res.data) ? res.data : [];
            })
            .then(() => {
                for (let i = todayDietBreakFast.value.length - 1; i >= 0; i--) {
                    if (!todayDietBreakFast.value[i] || todayDietBreakFast.value[i].img == null || todayDietBreakFast.value[i].img == '') continue;
                    else if (todayDietBreakFast.value[i] && todayDietBreakFast.value[i].img !== null) {
                        breakfastimg.value = todayDietBreakFast.value[i].img;
                        break;
                    }
                }

            })
    }

    // 점심 식단 기록 조회
    const getDietLunch = async function (loginUserId, regDate) {
        await axios.get(`${REST_DIET_API}/lunch`, {
            params: {
                loginUserId: loginUserId,
                regDate: regDate,
            },
            headers: {
                "Content-Type": "application/x-www-form-urlencode"
            }
        })
            .then((res) => {
                todayDietLunch.value = Array.isArray(res.data) ? res.data : [];
            })
            .then(() => {
                console.log(todayDietLunch.value)
                for (let i = todayDietLunch.value.length - 1; i >= 0; i--) {
                    if (!todayDietLunch.value[i] || todayDietLunch.value[i].img == null || todayDietLunch.value[i].img == '') continue;
                    else if (todayDietLunch.value[i] && todayDietLunch.value[i].img !== null) {
                        lunchimg.value = todayDietLunch.value[i].img;
                        break;
                    }
                }

            })
    }

    const modifyWeight = async function (newWeight) {
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
                router.push({ name: 'today' })
            )
            .catch((err) => {
                console.log('오류 : ' + err)
            })
    }

    //일단 아침을 기준으로 업로드
    const uploadDiet = async function (newDiet) {
        console.log(newDiet)
        await axios({
            url: `${REST_DIET_API}/upload`,
            method: 'POST',
            data: newDiet,
            headers: {
                "Content-Type": "application/json"
            },
        })
            .then(() => {
                if (newDiet.category == '아침')
                    getDietBreakFast(loginUserId, regDate)
                else if (newDiet.category == '점심')
                    getDietLunch(loginUserId, regDate)

                router.push({ name: 'today' })
            })
            .catch((err) => {
                console.log('오류 : ' + err)
            })
    }
    return { today, breakfastimg, lunchimg, dinnerimg, snackimg, todayDietBreakFast, todayDietLunch, todayDietDinner, todayDietSnack, getDietBreakFast, getDietLunch, uploadDiet }
})
