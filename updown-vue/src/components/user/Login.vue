<template>
    <div>       
        <div>
            <h3>로그인</h3>
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
            <RouterLink class="signup" :to="{ name: 'signup' }">회원가입</RouterLink>
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
.login{
    display: flex;
    flex-direction: column;
    justify-content: space-around;
}

h3{
    text-align: center;
}

.signup{
    text-align: center;
}
</style>