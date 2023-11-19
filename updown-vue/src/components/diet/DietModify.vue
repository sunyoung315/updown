<template>
    <div>
        <h2>{{ props.diet.category }}</h2>
        <img @click="submitForm" class="cursor" style="width: 2.5em;" src="../../asset/icon/save.png" alt="저장">
        <img class="cursor" @click="home" style="width: 2.3em;" src="../../asset/icon/home.png" alt="목록">
        <br>
        <label for="food">음식 이름</label><br>
        <input type="text" id="food" v-model="newDiet.food" :placeholder=props.diet.food>
        <br>
        <label for="calorie">음식 칼로리</label><br>
        <input type="number" id="calorie" v-model="newDiet.calorie" :placeholder=props.diet.calorie>
        <br>
        <label>음식사진(선택)</label>
        <div>
            <input ref="serveyImage" type="file" accept="image/*">
        </div>
    </div>
</template>

<script setup>
import { ref, onMounted } from 'vue';
import { useDietStore } from '@/stores/diet';
import axios from 'axios';

const today = new Date();
const year = today.getFullYear();
const month = ("0" + (1 + today.getMonth())).slice(-2);
const day = ("0" + today.getDate()).slice(-2);
const regDate = `${year}-${month}-${day}`;

const loginUserId = JSON.parse(localStorage.getItem("loginUser")).id;

const store = useDietStore();

const serveyImage = ref(null);

const props = defineProps({
    diet: Object,
})

// 수정할 식단
const newDiet = ref({
    no: props.diet.no,
    category: props.diet.category,
    food: '',
    calorie: '',
    img: '',
    orgImg: '',
    userId: loginUserId
});

console.log(props.diet)

onMounted(async () => {
    if (newDiet.value.category == '아침')
        await getDietBreakFast();
    else if (newDiet.value.category == '점심')
        await getDietLunch();
    else if (newDiet.value.category == '저녁')
        await getDietDinner();
    else if (newDiet.value.category == '간식')
        await getDietSnack();
})

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

const emits = defineEmits(["home"]);

// 이미지 업로드
const submitForm = async () => {
    // newDiet.value.food = props.diet.food;
    // newDiet.value.calorie=props.diet.calorie;
    if (serveyImage.value.files[0] == null) {
        store.modifyDiet(newDiet.value);
        emits("home")
        return;

    }
    const formData = new FormData();
    formData.append('file', serveyImage.value.files[0]);

    newDiet.value.img = serveyImage.value.files[0].name
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

    console.log(newDiet.value);
    store.modifyDiet(newDiet.value);
    emits("home")
};


const home = function () {
    emits("home")
}

</script>

<style scoped></style>