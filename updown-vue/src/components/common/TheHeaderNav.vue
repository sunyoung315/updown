<template>
    <header>
        <div class="header-container">
            <RouterLink to="today"><img class="cursor" style="width: 60px;" src="../../asset/icon/weight-scale.png" alt="로고"></RouterLink> 
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

@font-face {
    font-family: 'mobiletitle';
    src: url('/font/ONEMobileTitle.ttf')
}

@font-face {
    font-family: 'mobileregular';
    src: url('/font/ONE Mobile Regular/ONE Mobile Regular.ttf')
}

header {
    width: 100%;
    height: 100px;
    line-height: 100px;
    border-bottom: 1px solid rgb(199, 199, 199);
}
.header-container {
    display: flex;
    justify-content: space-between;
    padding-left: 200px;
    padding-right: 180px;
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