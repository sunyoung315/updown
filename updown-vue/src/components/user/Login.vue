<template>
    <div>
        <div class="login-container">
            <h2>로그인</h2>
            <br>
            <fieldset class="login">
                <label for="id">아 이 디</label>
                <input type="text" id="id" name="id" v-model="loginUser.id" placeholder="아이디를 입력해주세요.">
                <br>
                <label for="password">비 밀 번 호</label>
                <input type="password" id="password" name="password" v-model="loginUser.password"
                    placeholder="비밀번호를 입력해주세요.">
                <br>
                <button @click="login">로그인</button>
            </fieldset>
            <RouterLink :to="{ name: 'signup' }">회원가입</RouterLink>
        </div>
    </div>
</template>

<script setup>
import { ref } from 'vue';
import { useUserStore } from '@/stores/user';

const store = useUserStore();

const loginUser = ref({
    id: '',
    password: '',
})

const login = function () {
    store.login(loginUser.value);
}

</script>

<style scoped>
.login-container {
    width: 450px;
}

.login {
    display: flex;
    flex-direction: column;
    justify-content: space-around;
}

h2 {
    text-align: center;
    width: 450px;
}


input {
    border: 1px solid #929191;
    /* 테두리 굵기와 색을 변경합니다. */
    border-radius: 7.5px;
    height: 50px;
    width: 450px;
    
}

button {
    border: 1px solid #a8a7a7;
    /* 테두리 굵기와 색을 변경합니다. */
    border-radius: 7.5px;
    height: 50px;
    background-color: #a9a8a8;
    color: white;
    width: 450px;
}

a {
    display: inline-block;
    width: 450px;
    text-align: right;
    text-decoration: underline;
    color: #a8a7a7;
}
</style>