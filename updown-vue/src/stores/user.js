import { ref } from 'vue'
import { defineStore } from 'pinia';
import router from '@/router';
import axios from 'axios';

const REST_USER_API = `http://localhost:8080/updown/user`;

export const useUserStore = defineStore('user', () => {

    const matchedUser = ref('');

    let login = function(loginUser) {
        axios({
            url: `${REST_USER_API}/user/${loginUser.id}`,
            method: 'POST',
            data: loginUser,
            Headers: {
                "Content-Type": "application/json"
            },
        })
        .then((res) => {
            matchedUser.value = res.data;
            if(matchedUser.value) {
                alert("로그인 성공!");
                loginUser.id = '';
                loginUser.password = '';
                localStoarge.setItem("loginUser", matchedUser);
                router.push('/today');
            } else {
                alert("로그인 실패!");
                router.push('/');
            }
        })
        .catch((err) => {
            alert('서버 에러');
        })
    }

    return { matchedUser, login }
})