<template>
    <div>
        <div class="diet-head">
            <span class="diet-head-category">{{ category }}</span>
            <div>
                <img class="cursor" @click="submitForm"
                    src="../../asset/bootstrap-icon/save.svg" alt="저장">
                <img class="cursor" @click="home" id="home-btn"
                    src="../../asset/bootstrap-icon/house.svg" alt="목록">
            </div>
        </div>
        <div class="diet-box-flex">
            <div class="diet-box">
                <div class="diet-box-row">
                    <div class="diet-search">
                        <label>음식명 </label>
                        <img @click="search" id="search-btn" class="cursor" src="../../asset/bootstrap-icon/search.svg" alt="검색">
                    </div>
                    <div class="value" v-if="props.info.food"> {{ props.info.food }}</div>
                    <div v-else>
                        <input type="text" :class="{input : !foodName}" v-model="foodName" placeholder="음식을 직접 입력할 수도 있어요.">
                    </div>
                </div>
                <div class="diet-box-row">
                    <div class="g">
                        <label>섭취량</label><br>
                        <input type="number" :class="{input : !gram}" v-model="gram" placeholder="섭취량을 입력해주세요.">g
                    </div>
                </div>
                <div class="diet-box-row">
                    <div class="kcal">
                        <label>음식 칼로리</label><br>
                        <div class="value" v-if="props.info.food">{{ (props.info.calorie * gram).toFixed(1) }}kcal</div>
                        <div v-else>
                            <input type="text" :class="{input : !inputKcal}" v-model="inputKcal" placeholder="칼로리를 입력해주세요.">kcal
                        </div>
                    </div>
                </div>
                <div class="diet-box-row">
                    <div class="diet-box-img">
                        <label>음식 사진(선택)</label>
                        <div>
                            <input ref="serveyImage" type="file" accept="image/*" @change="changeImage">
                        </div>
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

onMounted(()=>{
    clearFileInput(); // 파일 인풋 초기화
})

const props = defineProps({
    category: String,
    info: Object,
});

const foodName = ref('');

const gram = ref('');

const inputKcal = ref('');

const loginUserId = JSON.parse(localStorage.getItem("loginUser")).id;

const serveyImage = ref('')

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

// 파일 초기화
const clearFileInput = () => {
  if (serveyImage.value) {
    serveyImage.value.value = '';
  }
};


// 이미지 업로드
const submitForm = async () => {
    if(props.info.food)
        newDiet.value.food = props.info.food;
    else
        newDiet.value.food = foodName.value;
    if (props.info.calorie)
        newDiet.value.calorie = parseFloat((props.info.calorie * gram.value).toFixed(1));
    else
        newDiet.value.calorie = inputKcal.value;

    if (serveyImage.value.files[0] == null) {
        if (props.category == '아침') {
            clearFileInput(); // 파일 인풋 초기화
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
    clearFileInput(); // 파일 인풋 초기화
    if (!newDiet.value.food) return; //음식명 입력 안하면 등록 막기
    if (!newDiet.value.calorie) return; //칼로리 0일 때 등록 막기
    await store.uploadDiet(newDiet.value);
    if (props.category == '아침')
        await getDietBreakFast();
    else if (props.category == '점심')
        await getDietLunch();
    else if (props.category == '저녁')
        await getDietDinner();
    else if (props.category == '간식')
        await getDietSnack();
    props.info.food = '';
    foodName.value = '';
    props.info.calorie = 0;
    inputKcal.value = '';
    gram.value = 0;
    previewImage.value = '';
    newDiet.value.img='';
    newDiet.value.orgImg='';
  
    emits("home");
}

const home = function () {
    props.info.food = '';
    foodName.value = '';
    props.info.calorie = 0;
    inputKcal.value = '';
    gram.value = 0;
    previewImage.value = '';
    newDiet.value.img='';
    newDiet.value.orgImg='';
    emits("home");
}


</script>

<style scoped>
.input {
    border: 0.2rem solid rgb(83, 120, 77);
    border-radius: 0.2rem;
}

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
.value {
    font-size: 0.9rem;
    font-weight: 600;
}
.diet-box {
    display: flex;
    flex-direction: column;
    margin-left: 0.5rem;
    width: 14.5rem;
    height: 14.3em;
}

label {
    padding-top: 0.5rem;
    font-size: 0.8rem;
}
.diet-search {
    display: flex;
    justify-items: baseline;
}

input {
    width: 11rem;
    height: 1.6rem;
    font-size: 0.7rem;
    margin-bottom: 0.15rem;
}
.cal-Kcal{
    font-size: 0.7rem;
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
