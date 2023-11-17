<template>
    <div>
        <h1>파일 업로드</h1>

        <form @submit.prevent="submitForm">
            <fieldset>
                <legend>사용자 정보 입력</legend>
                <label for="id">아이디</label>
                <input v-model="signupUser.id" type="text" id="id" name="id"><br>
                <label for="password">비밀번호</label>
                <input v-model="signupUser.password" autoComplete="off" type="password" id="password" name="password"><br>
                <label for="nickname">닉네임</label>
                <input v-model="signupUser.nickname" type="text" id="nickname" name="nickname"><br>
                <label for="name">이름</label>
                <input v-model="signupUser.name" type="text" id="name" name="name"><br>
                <label for="email">이메일</label>
                <input v-model="signupUser.email" type="text" id="email" name="email"><br>
                <label for="targetWeight">목표 체중</label>
                <input v-model="signupUser.targetWeight" type="number" id="targetWeight" name="targetWeight"><br>
                <label for="targetTime">하루 목표 운동 시간</label>
                <input v-model="signupUser.targetTime" type="number" id="targetTime" name="targetTime"><br>
                <label for="updown">증감량 희망 여부</label>
                <input v-model="signupUser.updown" type="text" id="updown" name="updown"><br>
                <label for="isSecret">계정 공개 희망 여부</label>
                <input v-model="signupUser.isSecret" type="checkbox" id="isSecret" name="isSecret"><br>
                <div>
                    <input ref="serveyImage" type="file" accept="image/*">
                </div>
                <input type="submit" value="등록">
                <input type="reset" value="초기화">
            </fieldset>
        </form>

    </div>
</template>

<script setup>
import { ref } from 'vue';
import { useRouter } from 'vue-router';
import axios from 'axios';

const router = useRouter();


const serveyImage = ref(null);


const signupUser = ref({
    id: '',
    password: '',
    nickname: '',
    name: '',
    email: '',
    targetWeight: '',
    targetTime: '',
    updown: '',
    isSecret: '',
    img: ''
})

// 이미지 업로드
const submitForm = async () => {
    const formData = new FormData();
    formData.append('file', serveyImage.value.files[0]);
    // 유저 정보
    signupUser.value.img = serveyImage.value.files[0]

    axios({
        url: `http://localhost:8080/updown/img/regist`,
        method: 'POST',
        data: formData,
        headers: {
            'Content-Type': 'multipart/form-data'
        },
    }).then((res) => {
        signupUser.value.img = res.data.img
        console.log("서버에서 다시 받아온 이미지: "+ res.data.img);
    }).then(()=> {
        // 이미지 업로드 후 회원가입 코드 실행
        goSignup();
    })
    

};


// 회원가입
const goSignup = function () {
    console.log("회원가입 메소드 안의 유저값:  "+ typeof(signupUser.value));
    axios({
        url: `http://localhost:8080/updown/user/signup`,
        method: 'POST',
        data: signupUser.value,
        headers: {
            "Content-Type": "application/json"
        },
    })
        .then(() => {
            router.push({ name: 'home' })
        })
}

</script>

<style scoped></style>