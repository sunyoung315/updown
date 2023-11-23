<template>
    <div class="myprofile-modify-container">
        <form @submit.prevent="submitForm">
            <div>
                <fieldset class="myprofile-modify">
                    <p class="title">나의 프로필</p>
                    <label for="password">비밀번호</label>
                    <input v-model="signupUser.password" autoComplete="off" type="password" id="password" name="password"
                        placeholder="비밀번호를 입력해주세요.">
                    <br><br>
                    <label for="password">비밀번호 확인</label>
                    <input v-model="password2" autoComplete="off" type="password" id="password2" name="password2"
                        placeholder="비밀번호를 입력해주세요.">
                    <br><br>
                    <label for="nickname">닉네임</label>
                    <input v-model="signupUser.nickname" type="text" id="nickname" name="nickname">
                    <br><br>
                    <label for="email">이메일</label>
                    <input v-model="signupUser.email" type="text" id="email" name="email">
                    <br><br>
                    <label for="targetWeight">목표 체중</label>
                    <input v-model="signupUser.targetWeight" type="number" id="targetWeight" name="targetWeight">
                    <a>
                        kg
                    </a>
                    <br>
                    <label for="targetTime">하루 목표 운동 시간</label>
                    <input v-model="signupUser.targetTime" type="number" id="targetTime" name="targetTime">
                    <a>
                        min
                    </a>

                    <label>프로필 사진(선택)</label>
                    <div class="input-group flex-nowrap" style="width: 540px;">
                        <input type="file" class="form-control" placeholder="Username" aria-label="Username"
                            aria-describedby="addon-wrapping =" ref="serveyImage" style="color: #a8a7a7; height: 35px;">
                    </div>


                    <br>
                    <input id="gomodify" type="submit" value="수정">
                    <div id="gohome">
                        <RouterLink to="/today">홈으로</RouterLink>
                    </div>
                </fieldset>
            </div>
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
    nickname: loginUser.nickname,
    name: loginUser.name,
    email: loginUser.email,
    targetWeight: loginUser.targetWeight,
    targetTime: loginUser.targetTime,
    updown: loginUser.updown,
    isSecret: loginUser.isSecret,
    img: loginUser.img,
    orgImg: loginUser.orgImg
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
    console.log('11')
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

    for (let i = 0; i < users.value; i++) {
        if (users.value[i].nickname == loginUser.nickname) continue;
        else if (user.value[i].nickname == signupUser.nickname) {
            alert('이미 존재하는 닉네임입니다.')
            return;
        }
    }

    if (signupUser.value.email === null || signupUser.value.email === '') {
        alert('이메일을 입력해주시기 바랍니다.')
        return;
    }

    if (!signupUser.value.email.includes('@')) {
        alert("이메일에 @가 포함되어야 합니다.")
        return;
    }

    for (let i = 0; i < users.value; i++) {
        if (users.value[i].email == loginUser.email) continue;
        else if (user.value[i].email == signupUser.email) {
            alert('이미 존재하는 이메일입니다.')
            return;
        }
    }

    if (signupUser.value.targetWeight === null || signupUser.value.targetWeight === '' || signupUser.value.targetWeight <= 0) {
        alert('목표 체중을 입력해주시기 바랍니다.')
        return;
    }

    if (signupUser.value.targetTime === null || signupUser.value.targetTime === '' || signupUser.value.targetTime <= 0) {
        alert('하루 목표 운동 시간을 입력해주시기 바랍니다.')
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

// beforeRouterEnter(to, from, next){
//     if(to.path ==='/today'){
//         localStorage.setItem("loginUser", JSON.stringify(signupUser.value));
//     }
// }

getUserList();
</script>

<style scoped>
.title {
    font-size: 2.1rem;
    text-align: center;
    margin-top: 2rem;
    margin-bottom: 3rem;
    font-weight: bold;
}
.myprofile-modify {
    display: flex;
    flex-direction: column;
    justify-content: center;
    width: 550px; 
    text-align: left;
    line-height: 20px;
    margin-top: 30px;
    margin-bottom: 150px;

}

h2 {
    text-align: center;
    margin-bottom: 40px;   
    font-size: 40px;
    margin-top: 20px;
    margin-right: 30px; 
}

input {
    border: 1px solid #929191;
    border-radius: 7.5px;
    height: 45px;
    width: 540px;
    font-size: 18px;
}

#gomodify {
    border: 1px solid #a8a7a7;
    border-radius: 7.5px;
    height: 40px;
    background-color: #a9a8a8;
    color: white;
}

.radio-container {
    display: flex;
    justify-content: space-between;
    width: 300px;

}

.radio {
    display: flex;
    vertical-align: middle;
}

.myprofile-modify-container {
    padding-top: 20px;
    margin-left: 350px;
    width: 900px;
    text-align: center;

}

a {
    width: 540px;
    text-align: right;
    text-decoration: underline;
    color: #a8a7a7;
    font-size: 17px;
}
#gohome {
    margin-top: 10px;
    text-align: right;
    width: 540px;
    font-size: 18px;
}

#img {
    color: #a8a7a7;
}

label{
    font-size: 1rem;
    font-weight: 600;
    margin-bottom: 0.5rem;
}
</style>
