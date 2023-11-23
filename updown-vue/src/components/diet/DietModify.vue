<template>
    <div>
        <div class="diet-head">
            <span class="diet-head-category">{{ props.diet.category }}</span>
            <div>
                <img class="cursor" @click="submitForm" src="../../asset/bootstrap-icon/save.svg" alt="저장">
                <img class="cursor" @click="home" id="home-btn" src="../../asset/bootstrap-icon/house.svg" alt="목록">
            </div>
        </div>
        <div class="diet-box-flex">
            <div class="diet-box">
                <div class="diet-box-row">
                    <label>음식명</label><br>
                    <input type="text" id="food" v-model="newDiet.food">
                    <p v-if="!newDiet.food">식단을 등록해주세요.</p>
                </div>
                <div class="diet-box-row">
                    <label>음식 칼로리</label><br>
                    <input type="number" id="calorie" v-model="newDiet.calorie">kcal
                </div>
                <div class="diet-box-row">
                    <label>음식 사진(선택)</label>
                    <div>
                        <input ref="serveyImage" type="file" accept="image/*" @change="changeImage">
                    </div>
                </div>
            </div>
            <div class="preview">
                <div v-if="props.diet.img">
                    <img class="preview-img" :src="previewImage" alt="이미지 미리보기">
                </div>
                <div v-else style="color: gray;">
                    No Image
                </div>
            </div>
        </div>
    </div>
</template>

<script setup>
import { ref, onMounted, watchEffect } from 'vue';
import { useDietStore } from '@/stores/diet';
import axios from 'axios';

const props = defineProps({
    diet: Object,
})

const previewImage = ref(`http://localhost:8080/upload/${props.diet.img}`);
const changeImage = function(event) {
    const files = event.target.files;
    if(files.length > 0) {
        const file = files[0];
        // FileReader 객체 : 웹 애플리케이션이 데이터를 읽고, 저장하게 해줌
        const reader = new FileReader();
        // load 이벤트 핸들러. 리소스 로딩이 완료되면 실행됨
        reader.onload = function(e) {
            previewImage.value = e.target.result
        }
        // 컨텐츠를 특정 file에서 읽어옴. 읽는 행위가 종료되면 loadend 이벤트 트리거함 
        // & base64 인코딩된 스트링 데이터가 result 속성에 담김
        reader.readAsDataURL(file);
    }
}

const today = new Date();
const year = today.getFullYear();
const month = ("0" + (1 + today.getMonth())).slice(-2);
const day = ("0" + today.getDate()).slice(-2);
const regDate = `${year}-${month}-${day}`;

const loginUserId = JSON.parse(localStorage.getItem("loginUser")).id;

const store = useDietStore();

const serveyImage = ref(null);


// 수정할 식단
let newDiet = ref({
    no: props.diet.no,
    category: props.diet.category,
    food: props.diet.food,
    calorie: props.diet.calorie,
    img: props.diet.img,
    orgImg: props.diet.orgimg,
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
    if(!newDiet.value.food) return;
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
    width: 26rem;
    justify-content: space-between;
    margin-top: 0.3em;
}
.diet-head-category {
    font-size: 1.5rem;
    font-weight: 600;
    padding-left: 0.3em;
}
.diet-box-flex {
    display: flex;
    height: 8em; 
    margin-top: 0.5rem;
}
.diet-box {
    display: flex;
    flex-direction: column;
    margin-left: 0.5rem;
    width: 14.5rem;
    height: 14.3em;
}
label {
    font-size: 20px;
}
.diet-box-row {
    padding-top: 0.5rem;
    font-size: 0.8rem;
    font-weight: 600;
}
label {
    padding-top: 0.5rem;
    font-size: 0.8rem;
    font-weight: 600;
}
input {
    width: 11rem;
    height: 1.6rem;
    font-size: 0.8rem;
    margin-bottom: 0.15rem;
}
.preview {
    width: 10.5em;
    height: 14.3em;
    background-color: rgb(225, 225, 225);
    margin-top: 0.1em;
    line-height: 14.3em;
    vertical-align: middle;
    text-align: center;
}
.preview-img {
    width: 9.5em;
    margin-left: 0.5em;
    margin-right: 0.5em;
}
.cursor {
    width: 2rem;
    margin: 0rem 0 0.25rem 1rem;
}
#home-btn {
    width: 2.3rem;
    margin: 0rem 0.5rem 0.25rem 0.7rem;
}
#search-btn {
    width: 2rem;
    padding-left: 0.5rem;
    padding-bottom: 0.5rem;
    margin: 0;
}
</style>