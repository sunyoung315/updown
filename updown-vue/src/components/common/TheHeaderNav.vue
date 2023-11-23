<template>
    <header>
        <div class="header-container">
            <RouterLink to="today"><img class="cursor" style="width: 150px;" src="../../asset/image/updown-logo.jpg" alt="로고"></RouterLink> 
            <nav>
                <RouterLink :to="{name: 'today'}" style="font-size: 20px; padding-right: 30px;">오늘의 기록</RouterLink>|
                <RouterLink :to="{name: 'record'}" style="font-size: 20px; padding-left: 30px;">과거 기록 조회</RouterLink>
            </nav>
            <div>
                <RouterLink :to="{ name: 'myprofile' }" style="padding-right: 30px;">
                    <img class="cursor" style="width: 46px;" src="../../asset/bootstrap-icon/person-square.svg" alt="마이프로필">
                </RouterLink>
                <img class="cursor" @click="logout"  style="width: 60px;" src="../../asset/bootstrap-icon/box-arrow-right.svg" alt="로그아웃">
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
    margin-top: 20px;
    /* margin-right: 500px; */
}

header {
    width: 100%;
    height: 140px;
    line-height: 140px;
    border-bottom: 1px solid rgb(199, 199, 199);
}
.header-container {
    font-family: 'IBM Plex Sans', sans-serif;
    display: flex;
    justify-content: space-between;
    padding-left: 280px;
    padding-right: 320px;
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