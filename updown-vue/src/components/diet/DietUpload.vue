<template>
    <div>
        <h2>{{ category }}</h2>
        <img @click="submitForm" class="cursor" style="width: 2.5em;" src="../../asset/icon/save.png" alt="저장">
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
import { ref, onMounted, reactive } from 'vue';
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

onMounted(async () => {
    await getDietBreakFast();
})

const getDietBreakFast = async function () {
    await store.getDietBreakFast(loginUserId, regDate);
}


// 이미지 업로드
const submitForm = async () => {
    if (serveyImage.value.files[0] == null) {
        if (props.category == '아침') {
            newDiet.value.category = "아침"
            upload();
            return;
        }
        if (props.category == '점심') {
            newDiet.value.category = "점심"
            return;
        }
        if (props.category == '저녁') {
            newDiet.value.category = "저녁"
            return;
        }
        if (props.category == '간식') {
            newDiet.value.category = "간식"
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
        store.newDietLunch.value.img = serveyImage.value.files[0]
        axios({
            url: `http://localhost:8080/updown/img/regist`,
            method: 'POST',
            data: formData,
            headers: {
                'Content-Type': 'multipart/form-data'
            },
        }).then((res) => {
            store.newDietLunch.value.img = res.data.img
            store.newDietLunch.value.orgImg = res.data.orgImg
        })
    }
    else if (props.category == '저녁') {
        store.newDietDinner.value.img = serveyImage.value.files[0]
        axios({
            url: `http://localhost:8080/updown/img/regist`,
            method: 'POST',
            data: formData,
            headers: {
                'Content-Type': 'multipart/form-data'
            },
        }).then((res) => {
            store.newDietDinner.value.img = res.data.img
            store.newDietDinner.value.orgImg = res.data.orgImg
        })
    }
    else if (props.category == '간식') {
        store.newDietSnack.value.img = serveyImage.value.files[0]
        axios({
            url: `http://localhost:8080/updown/img/regist`,
            method: 'POST',
            data: formData,
            headers: {
                'Content-Type': 'multipart/form-data'
            },
        }).then((res) => {
            store.newDietSnack.value.img = res.data.img
            store.newDietSnack.value.orgImg = res.data.orgImg
        })
    }
    upload();

};

// 일단 아침을 기준으로 업로드
const upload = async function () {
    await store.uploadDiet(newDiet.value);
    await getDietBreakFast();
    emits("home");
}

</script>

<style scoped></style>