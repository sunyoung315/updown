<template>
    <div>
        <h1>회원가입</h1>

        <form @submit.prevent="submitForm">
            <fieldset>
                <legend>사용자 정보 입력</legend>
                <br>
                <label for="id">아이디</label><br>
                <input v-model="signupUser.id" type="text" id="id" name="id">
                <br>
                <label for="password">비밀번호</label><br>
                <input v-model="signupUser.password" autoComplete="off" type="password" id="password" name="password">
                <br>
                <label for="password">비밀번호 확인</label><br>
                <input v-model="password2" autoComplete="off" type="password" id="password2" name="password2">
                <br>
                <label for="nickname">닉네임</label><br>
                <input v-model="signupUser.nickname" type="text" id="nickname" name="nickname">
                <br>
                <label for="name">이름</label><br>
                <input v-model="signupUser.name" type="text" id="name" name="name">
                <br>
                <label for="email">이메일</label><br>
                <input v-model="signupUser.email" type="text" id="email" name="email">
                <br>
                <label for="targetWeight">목표 체중</label><br>
                <input v-model="signupUser.targetWeight" type="number" id="targetWeight" name="targetWeight">
                <br>
                <label for="targetTime">하루 목표 운동 시간</label><br>
                <input v-model="signupUser.targetTime" type="number" id="targetTime" name="targetTime">
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

                <input type="submit" value="회원가입">
                <RouterLink to="/"><button>홈으로</button></RouterLink>
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

// 비밀번호 확인용
const password2 = ref('')

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
    img: '',
    orgImg: ''
})

// 이미지 업로드
const submitForm = async () => {
    if (serveyImage.value.files[0] == null) {
        goSignup();
        return;
    }

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
        signupUser.value.orgImg = res.data.orgImg
    }).then(() => {
        // 이미지 업로드 후 회원가입 코드 실행
        goSignup();
    })


};


// 회원가입
const goSignup = function () {
    
  if(!email.value.includes('@')){
    alert("이메일에 @가 포함되어야 합니다.")
    return;
  }

  // \W : 공백을 포함한 특수문자
  // /\W/ : 시작기호, 특수문자, 끝기호
  // some() 메서드는 배열 안의 어떤 요소라도 주어진 판별 함수를 적어도 하나라도 통과하는지 테스트함
  if(signupUser.value.password.length < 8 || signupUser.value.password.length >20 || !/\W/.test(signupUser.value.password)){
    alert("비밀번호는 8자리 이상 20자리 이하이며, 특수문자를 포함해야 합니다.")
    return;
  }

  if (signupUser.value.password !== password2.value) {
    alert('비밀번호가 일치하지 않습니다.');
    return;
  }

  // some() : 특정 조건을 만족하는 요소가 있는지 검사하는 코드
  if (signupUser.value.some(user => user.id === id.value)) {
    alert('이미 존재하는 아이디입니다.');
    return;
  }

    axios({
        url: `http://localhost:8080/updown/user/signup`,
        method: 'POST',
        data: signupUser.value,
        headers: {
            "Content-Type": "application/json"
        },
    })
        .then(() => {
            router.push({ name: 'login' })
        })
}

</script>

<style scoped></style>