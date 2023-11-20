<template>
    <div>
        <h2>마이프로필</h2>
        <form @submit.prevent="submitForm">
            <fieldset>
                <label for="password">비밀번호</label><br>
                <input v-model="signupUser.password" autoComplete="off" type="password" id="password" name="password">
                <br>
                <label for="password">비밀번호 확인</label><br>
                <input v-model="password2" autoComplete="off" type="password" id="password2" name="password2">
                <br>
                <label for="nickname">닉네임</label><br>
                <input v-model="signupUser.nickname" type="text" id="nickname" name="nickname">
                <br>
                <label for="email">이메일</label><br>
                <input v-model="signupUser.email" type="text" id="email" name="email">
                <br>
                <label for="targetWeight">목표 체중</label><br>
                <input v-model="signupUser.targetWeight" type="number" id="targetWeight" name="targetWeight">kg
                <br>
                <label for="targetTime">하루 목표 운동 시간</label><br>
                <input v-model="signupUser.targetTime" type="number" id="targetTime" name="targetTime">min
                <br>
                <label>증감량 희망 여부</label><br>{{ signupUser.updown }}
                <input v-model="signupUser.updown" type="radio" id="up" value="up">
                <label for="up">증량</label>
                <input v-model="signupUser.updown" type="radio" id="down" value="down">
                <label for="down">감량</label>
                <br>
                <label for="isSecret">계정 공개 희망 여부</label><br>{{ signupUser.isSecret }}
                <input v-model="signupUser.isSecret" type="radio" id="isSecretTrue" :value="true">
                <label for="isSecretTrue">공개</label>
                <input v-model="signupUser.isSecret" type="radio" id="isSecretFalse" :value="false">
                <label for="isSecretFalse">비공개</label>
                <br>
    
                <label>프로필 사진(선택)</label>
                <div>
                    <input ref="serveyImage" type="file" accept="image/*">
                </div>
    
                <RouterLink to="/">홈으로</RouterLink>
                <input type="submit" value="수정">
            </fieldset>
        </form>
    </div>
</template>

<script setup>
import { ref } from 'vue';
import { useRouter } from 'vue-router';
import axios from 'axios';

const loginUser = JSON.parse(localStorage.getItem("loginUser"));

const router = useRouter();

const serveyImage = ref(null);

// 비밀번호 확인용
const password2 = ref('')

const signupUser = ref({
    id: loginUser.id,
    password: '',
    nickname: '',
    name: loginUser.name,
    email: '',
    targetWeight: '',
    targetTime: '',
    updown: '',
    isSecret: '',
    img: '',
    orgImg: ''
})

// 전체 유저
const users = ref([]);

const REST_UPDOWN_API = 'http://localhost:8080/updown';

// 모든 유저 가져오는 메서드
const getUserList = () => {
    axios({
        url: `${REST_UPDOWN_API}/user`,
        method: "GET"
    })
        .then((res) => {
            users.value = Array.isArray(res.data) ? res.data : [];
        })
        .catch((err) => {
            console.log(err);
        })
}
// 이미지 업로드
const submitForm = async () => {
    if (serveyImage.value.files[0] == null) {
        goModifyProfile();
        return;
    }

    const formData = new FormData();
    formData.append('file', serveyImage.value.files[0]);
    // 유저 정보
    signupUser.value.img = serveyImage.value.files[0]

    axios({
        url: `${REST_UPDOWN_API}/img/regist`,
        method: 'POST',
        data: formData,
        headers: {
            'Content-Type': 'multipart/form-data'
        },
    }).then((res) => {
        signupUser.value.img = res.data.img
        signupUser.value.orgImg = res.data.orgImg
    }).then(() => {
        // 이미지 업로드 후 회원가입 코드 실행
        goModifyProfile();
    })
};


// 프로필 수정
const goModifyProfile = function () {
    // \W : 공백을 포함한 특수문자
    // /\W/ : 시작기호, 특수문자, 끝기호
    // some() 메서드는 배열 안의 어떤 요소라도 주어진 판별 함수를 적어도 하나라도 통과하는지 테스트함
    if (signupUser.value.password.length < 8 || signupUser.value.password.length > 20 || !/\W/.test(signupUser.value.password)) {
        alert("비밀번호는 8자리 이상 20자리 이하이며, 특수문자를 포함해야 합니다.")
        return;
    }
    
    if (signupUser.value.password !== password2.value) {
        alert('비밀번호가 일치하지 않습니다.');
        return;
    }
    
    if (signupUser.value.nickname === null || signupUser.value.nickname === '') {
        alert('닉네임을 입력해주시기 바랍니다.')
        return;
    }

    if (users.value.some(user => user.nickname === nickname.value)) {
        alert('이미 존재하는 닉네임입니다.');
        return;
    }

    if (signupUser.value.email === null || signupUser.value.email === '') {
        alert('이메일을 입력해주시기 바랍니다.')
        return;
    }

    if (!signupUser.value.email.includes('@')) {
        alert("이메일에 @가 포함되어야 합니다.")
        return;
    }

    if (users.value.some(user => user.email == email.value)) {
        alert('이미 존재하는 이메일입니다.');
        return;
    }

    if (signupUser.value.targetWeight === null || signupUser.value.targetWeight === '' || signupUser.value.targetWeight <= 0) {
        alert('목표 체중을 입력해주시기 바랍니다.')
        return;
    }

    if (signupUser.value.targetTime === null || signupUser.value.targetTime === '' || signupUser.value.targetTime <= 0) {
        alert('하루 목표 운동 시간을 입력해주시기 바랍니다.')
        return;
    }

    if (signupUser.value.updown === null || signupUser.value.updown === '') {
        alert('증감량 희망 여부를 선택해주시기 바랍니다.')
        return;
    }
    if (signupUser.value.isSecret === null || signupUser.value.isSecret === '') {
        alert('계정 공개 희망 여부를 선택주시기 바랍니다.')
        return;
    }


    axios({
        url: `${REST_UPDOWN_API}/user/modify`,
        method: 'PUT',
        data: signupUser.value,
        headers: {
            "Content-Type": "application/json"
        },
    })
    .then(() => {
        localStorage.setItem("loginUser", JSON.stringify(signupUser.value));
        router.push({ name: 'today' })
    })
}

getUserList();
</script>

<style scoped>

</style>