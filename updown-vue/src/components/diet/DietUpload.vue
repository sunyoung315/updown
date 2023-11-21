<template>
    <div>
        <div class="diet-head">
            <span class="diet-head-category">{{ category }}</span>
            <div>
                <img class="cursor" @click="submitForm" style="width: 43px; margin: 5px 5px 0px 5px;"
                    src="../../asset/bootstrap-icon/save.svg" alt="저장">
                <img class="cursor" @click="home" style="width: 50px; margin: 5px 0px 0px 5px;"
                    src="../../asset/bootstrap-icon/house.svg" alt="목록">
            </div>
        </div>
        <div class="diet-box-flex">
            <div class="diet-box">
                <div class="diet-box-row">
                        <label>음식 검색 </label>
                        <img @click="search" class="cursor" style="width: 43px; margin: 0px 5px 20px 8px;"
                            src="../../asset/bootstrap-icon/search.svg" alt="검색">
                    <input type="text" v-if="props.info && props.info.food" v-model="props.info.food">
                    <p v-else>식단을 등록해주세요.</p>
                </div>
                <div class="diet-box-row">
                    <label>음식 칼로리</label><br>
                    <input type="number" v-if="props.info && props.info.calorie" v-model="props.info.calorie">kcal
                </div>
                <div class="diet-box-row">
                    <label>음식사진(선택)</label>
                    <div>
                        <input ref="serveyImage" type="file" accept="image/*" @change="changeImage">
                    </div>
                </div>
            </div>
            <div class="preview">
                <div v-if="previewImage">
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
import { ref, onMounted } from 'vue';
import { useDietStore } from '@/stores/diet';
import axios from 'axios';


const previewImage = ref('');
const changeImage = function (event) {
    const files = event.target.files;
    if (files.length > 0) {
        const file = files[0];
        // FileReader 객체 : 웹 애플리케이션이 데이터를 읽고, 저장하게 해줌
        const reader = new FileReader();
        // load 이벤트 핸들러. 리소스 로딩이 완료되면 실행됨
        reader.onload = function (e) {
            previewImage.value = e.target.result
        }
        // 컨텐츠를 특정 file에서 읽어옴. 읽는 행위가 종료되면 loadend 이벤트 트리거함 
        // & base64 인코딩된 스트링 데이터가 result 속성에 담김
        reader.readAsDataURL(file);
    }
}

const props = defineProps({
    category: String,
    info: Object,
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
    category: props.category,
    food: '',
    calorie: '',
    img: '',
    orgImg: '',
    userId: loginUserId
});


const emits = defineEmits(["home", "search"])

const store = useDietStore();


const search = function () {
    emits("search");

}

onMounted(() => {
    props.info.food = '';
    props.info.calorie = '';
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


// 이미지 업로드
const submitForm = async () => {
    newDiet.value.food = props.info.food;
    newDiet.value.calorie = props.info.calorie;
    console.log("----upload---")
    console.log(serveyImage.value)
    console.log(newDiet.value)
    console.log(previewImage.value)
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
            newDiet.value.img = res.data.img
            newDiet.value.orgImg = res.data.orgImg
            props.info.food = ''
            props.info.calorie = ''
        })
    }
    upload();
};

const upload = async function () {
    if (!newDiet.value.food) return;
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

const home = function () {
    previewImage.value = '';
    emits("home");
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

.diet-box-flex {
    display: flex;
    height: 260px;
    margin-top: 20px;
}

.diet-box {
    margin-left: 25px;
    width: 290px;
    height: 260px;
}

label {
    font-size: 20px;
}

.diet-box-row {
    margin-top: 5px;
    margin-bottom: 15px;
}

input {
    width: 240px;
    height: 40px;
}

.preview {
    width: 204px;
    height: 260px;
    line-height: 260px;
    border: 1px solid rgb(202, 202, 202);
    text-align: center;
    vertical-align: middle;
    margin-left: 10px;
}

.preview-img {
    width: 180px;
    margin-left: 10px;
    margin-right: 10px;
    vertical-align: middle;
}

p {
    font-size: 11px;
    color: red;
}
</style>
