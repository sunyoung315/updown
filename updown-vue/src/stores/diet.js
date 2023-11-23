import { ref } from 'vue'
import { defineStore } from 'pinia';
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

    const todayDietList = ref([]);

    // 오늘 전체 식단 기록 조회
    const getTodayDietList = async function (loginUserId, regDate) {
        await axios.get(`${REST_DIET_API}`, {
            params: {
                loginUserId: loginUserId,
                regDate: regDate,
            },
            headers: {
                "Content-Type": "application/x-www-form-urlencode"
            }
        })
            .then((res) => {
                todayDietList.value = res.data;
            })
    }


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
                for (let i = todayDietLunch.value.length - 1; i >= 0; i--) {
                    if (!todayDietLunch.value[i] || todayDietLunch.value[i].img == null || todayDietLunch.value[i].img == '') continue;
                    else if (todayDietLunch.value[i] && todayDietLunch.value[i].img !== null) {
                        lunchimg.value = todayDietLunch.value[i].img;
                        break;
                    }
                }

            })
    }

    // 저녁 식단 기록 조회
    const getDietDinner = async function (loginUserId, regDate) {
        await axios.get(`${REST_DIET_API}/dinner`, {
            params: {
                loginUserId: loginUserId,
                regDate: regDate,
            },
            headers: {
                "Content-Type": "application/x-www-form-urlencode"
            }
        })
            .then((res) => {
                todayDietDinner.value = Array.isArray(res.data) ? res.data : [];
            })
            .then(() => {
                for (let i = todayDietDinner.value.length - 1; i >= 0; i--) {
                    if (!todayDietDinner.value[i] || todayDietDinner.value[i].img == null || todayDietDinner.value[i].img == '') continue;
                    else if (todayDietDinner.value[i] && todayDietDinner.value[i].img !== null) {
                        dinnerimg.value = todayDietDinner.value[i].img;
                        break;
                    }
                }

            })
    }

    // 간식 식단 기록 조회
    const getDietSnack = async function (loginUserId, regDate) {
        await axios.get(`${REST_DIET_API}/snack`, {
            params: {
                loginUserId: loginUserId,
                regDate: regDate,
            },
            headers: {
                "Content-Type": "application/x-www-form-urlencode"
            }
        })
            .then((res) => {
                todayDietSnack.value = Array.isArray(res.data) ? res.data : [];
            })
            .then(() => {
                for (let i = todayDietSnack.value.length - 1; i >= 0; i--) {
                    if (!todayDietSnack.value[i] || todayDietSnack.value[i].img == null || todayDietSnack.value[i].img == '') continue;
                    else if (todayDietSnack.value[i] && todayDietSnack.value[i].img !== null) {
                        snackimg.value = todayDietSnack.value[i].img;
                        break;
                    }
                }
            })
    }

    const remove = async function (diet) {
        await axios({
            url: `${REST_DIET_API}/remove/${diet.no}`,
            method: 'DELETE',
            data: diet.no
        });
        breakfastimg.value = ''; // 이미지 초기화
        await getDietBreakFast(loginUserId, regDate); // 식단 목록 다시 불러오기
        lunchimg.value = '';
        await getDietLunch(loginUserId, regDate);
        dinnerimg.value = '';
        await getDietDinner(loginUserId, regDate);
        snackimg.value = '';
        await getDietSnack(loginUserId, regDate);
    }

    // 식단 수정
    const modifyDiet = async function (newDiet) {
        await axios({
            url: `${REST_DIET_API}/modify`,
            method: 'PUT',
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
                else if (newDiet.category == '저녁')
                    getDietDinner(loginUserId, regDate)
                else if (newDiet.category == '간식')
                    getDietSnack(loginUserId, regDate)

                router.push({ name: 'today' })
            })
            .catch((err) => {
                console.log('오류 : ' + err)
            })
    }


    // 식단 등록
    const uploadDiet = async function (newDiet) {
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
                else if (newDiet.category == '저녁')
                    getDietDinner(loginUserId, regDate)
                else if (newDiet.category == '간식')
                    getDietSnack(loginUserId, regDate)

                // 등록 후 초기화
                newDiet.category = ''
                newDiet.food = ''
                newDiet.calorie = ''
                newDiet.Img = ''
                newDiet.userId = loginUserId
                router.push({ name: 'today' })
            })
            .catch((err) => {
                console.log('오류 : ' + err)
            })
    }


    return { today, remove, modifyDiet, breakfastimg, lunchimg, dinnerimg, snackimg, todayDietBreakFast, todayDietLunch, todayDietDinner, todayDietSnack, getDietBreakFast, getDietLunch, getDietDinner, getDietSnack, uploadDiet, getTodayDietList, todayDietList }
})
