<template>
    <div>
        <div class="diet-head">
            <span class="diet-head-category">{{ props.diet.category }}</span>
            <div>
                <img class="cursor" @click="submitForm" style="width: 43px; margin: 5px 5px 0px 5px;" src="../../asset/bootstrap-icon/save.svg" alt="저장">
                <img class="cursor" @click="home" style="width: 50px; margin: 5px 0px 0px 5px;" src="../../asset/bootstrap-icon/house.svg" alt="목록">
            </div>
        </div>
        <br>
        <label for="food">음식 이름</label><br>
        <input type="text" id="food" v-model="newDiet.food" :placeholder=props.diet.food>
        <br>
        <label for="calorie">음식 칼로리</label><br>
        <input type="number" id="calorie" v-model="newDiet.calorie" :placeholder=props.diet.calorie>kcal
        <br>
        <label>음식사진(선택)</label>
        <div>
            <input ref="serveyImage" type="file" accept="image/*">
        </div>
    </div>
</template>

<script setup>
import { ref, onMounted, watchEffect } from 'vue';
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
let newDiet = ref({
    no: props.diet.no,
    category: props.diet.category,
    food:'',
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

// watchEffect : 주어진 함수를 즉시 실행, 함수 내에서 사용된 반응형 속성들의 변경을 감시, 의존성이 있는 데이터에 대해서 즉각적으로 실행 (immediately)
// 즉, props.diet의 어떤 속성이든 변경되면 newDiet.value가 해당 변경 사항을 반영한 새로운 객체로 설정
// props.diet의 값이 변경될 때마다 newDiet.value의 값도 그에 따라 자동으로 업데이트
watchEffect(() => {
    newDiet.value = {
        no: props.diet.no,
        category: props.diet.category,
        food: props.diet.food,
        calorie: props.diet.calorie,
        img: props.diet.img,
        orgImg: props.diet.orgImg,
        userId: loginUserId
    };
});

const home = function () {
    emits("home")
}

</script>

<style scoped>
.diet-head {
    display: flex;
    justify-content: space-between;
}
.diet-head-category {
    font-size: 40px;
    padding-left: 10px;
}
</style>