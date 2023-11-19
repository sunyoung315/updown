<template>
    <div>
        <h2>{{ category }}</h2>
        <img @click="submitForm" class="cursor" style="width: 2.5em;" src="../../asset/icon/save.png" alt="저장">
        <img class="cursor" @click="home" style="width: 2.3em;" src="../../asset/icon/home.png" alt="목록">
        <br>
        <label>음식 이름</label><br>
        <input type="text" v-model="newDiet.food" placeholder='식단을 등록해주세요.'>
        <br>
        <label>음식 칼로리</label><br>
        <input type="number" v-model="newDiet.calorie" placeholder='kcal'>
        <br>
        <label>음식사진(선택)</label>
        <div>
            <input ref="serveyImage" type="file" accept="image/*">
        </div>
    </div>
</template>

<script setup>
import { ref, onMounted, } from 'vue';
import { useDietStore } from '@/stores/diet';
import axios from 'axios';

const props = defineProps({
    category: String,
});

const serveyImage = ref(null);

const loginUserId = JSON.parse(localStorage.getItem("loginUser")).id;

const today = new Date();
const year = today.getFullYear();
const month = ("0" + (1 + today.getMonth())).slice(-2);
const day = ("0" + today.getDate()).slice(-2);
const regDate = `${year}-${month}-${day}`;

// 등록할 식단
const newDiet = ref({
    category: '',
    food: '',
    calorie: '',
    img: '',
    orgImg: '',
    userId: loginUserId
});

const emits = defineEmits(["home",])

const store = useDietStore();

const getDietBreakFast = async function () {
    await store.getDietBreakFast(loginUserId, regDate);
}
const getDietLunch = async function () {
    await store.getDietLunch(loginUserId, regDate);
}
const getDietDinner = async function () {
    await store.getDietDinner(loginUserId, regDate);
}
const getDietSnack = async function () {
    await store.getDietSnack(loginUserId, regDate);
}


// 이미지 업로드
const submitForm = async () => {
    if (serveyImage.value.files[0] == null) {
        if (props.category == '아침') {
            newDiet.value.category = "아침"
            upload();
            return;
        }
        else if (props.category == '점심') {
            newDiet.value.category = "점심"
            upload();
            return;
        }
        else if (props.category == '저녁') {
            newDiet.value.category = "저녁"
            upload();
            return;
        }
        else if (props.category == '간식') {
            newDiet.value.category = "간식"
            upload();
            return;
        }
    }

    const formData = new FormData();
    formData.append('file', serveyImage.value.files[0]);

    if (props.category == '아침') {
        newDiet.value.img = serveyImage.value.files[0].name
        newDiet.value.category = "아침"
        await axios({
            url: `http://localhost:8080/updown/img/regist`,
            method: 'POST',
            data: formData,
            headers: {
                'Content-Type': 'multipart/form-data'
            },
        }).then((res) => {
            console.log(res.data)
            newDiet.value.img = res.data.img
            newDiet.value.orgImg = res.data.orgImg
        })
    }
    else if (props.category == '점심') {
        newDiet.value.img = serveyImage.value.files[0].name
        newDiet.value.category = "점심"
        await axios({
            url: `http://localhost:8080/updown/img/regist`,
            method: 'POST',
            data: formData,
            headers: {
                'Content-Type': 'multipart/form-data'
            },
        }).then((res) => {
            console.log(res.data)
            newDiet.value.img = res.data.img
            newDiet.value.orgImg = res.data.orgImg
        })
    }
    else if (props.category == '저녁') {
        newDiet.value.img = serveyImage.value.files[0].name
        newDiet.value.category = "저녁"
        await axios({
            url: `http://localhost:8080/updown/img/regist`,
            method: 'POST',
            data: formData,
            headers: {
                'Content-Type': 'multipart/form-data'
            },
        }).then((res) => {
            console.log(res.data)
            newDiet.value.img = res.data.img
            newDiet.value.orgImg = res.data.orgImg
        })
    }
    else if (props.category == '간식') {
        newDiet.value.img = serveyImage.value.files[0].name
        newDiet.value.category = "간식"
        await axios({
            url: `http://localhost:8080/updown/img/regist`,
            method: 'POST',
            data: formData,
            headers: {
                'Content-Type': 'multipart/form-data'
            },
        }).then((res) => {
            console.log(res.data)
            newDiet.value.img = res.data.img
            newDiet.value.orgImg = res.data.orgImg
        })
    }
    upload();

};

const upload = async function () {
    await store.uploadDiet(newDiet.value);
   if (props.category == '아침')
    await getDietBreakFast();
    else if (props.category == '점심')
        await getDietLunch();
    else if (props.category == '저녁')
        await getDietDinner();
    else if (props.category == '간식')
        await getDietSnack();
    emits("home");
}

const home = function() {
    emits("home");
}


</script>

<style scoped></style>