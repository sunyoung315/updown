<template>
    <header>
        <div class="header-container">
            <RouterLink to="today"><img class="cursor" style="width: 150px;" src="../../asset/image/updown-logo.jpg" alt="로고"></RouterLink> 
            <nav>
                <RouterLink :to="{name: 'today'}">오늘의 기록</RouterLink>
                <RouterLink :to="{name: 'record'}">과거 기록 조회</RouterLink>
            </nav>
            <div>
                <RouterLink :to="{ name: 'myprofile' }" style="padding-right: 30px;">
                    <img class="cursor" style="width: 2.2rem;" src="../../asset/bootstrap-icon/person-square.svg" alt="마이프로필">
                </RouterLink>
                <img class="cursor" @click="logout"  style="width: 3rem;" src="../../asset/bootstrap-icon/box-arrow-right.svg" alt="로그아웃">
            </div>
        </div>
    </header>
</template>

<script setup>
import { useRouter } from 'vue-router';

const router = useRouter();

const logout = function() {
    localStorage.removeItem("loginUser");
    router.push("/");
}
</script>

<style>
nav {
    width: 17rem;
    margin-top: 2rem;
    margin-right: 27rem;
    font-size: 1.2rem;
    font-weight: 600;
    display: flex;
    justify-content: space-between;
}

header {
    width: 100%;
    height: 9rem;
    line-height: 9rem;
    border-bottom: 1px solid rgb(199, 199, 199);
}
.header-container {
    font-family: 'IBM Plex Sans', sans-serif;
    display: flex;
    justify-content: space-between;
    padding-left:15rem;
    padding-right: 21rem;
}
.cursor {
    cursor: pointer;
}
* {
    font-family: 'mobiletitle';
    color: black;
}
a {
    text-decoration: none;
    color: black;
}
input::-webkit-outer-spin-button,
input::-webkit-inner-spin-button {
  -webkit-appearance: none;
  margin: 0;
}
</style>