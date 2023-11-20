<template>
    <header>
        <div class="header-container">
            <RouterLink to="today"><h2>로고</h2></RouterLink> 
            <nav>
                <RouterLink :to="{name: 'today'}">오늘의 기록</RouterLink> | 
                <RouterLink :to="{name: 'record'}">과거 기록 조회</RouterLink>
            </nav>
            <img class="cursor" @click="logout"  style="width: 3em; height: 3em" src="../../asset/icon/out.png" alt="로그아웃">
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
    font-family: 'hanna';
    src: url('/font/BMHANNAPro.ttf')
}
header {
    border: 1px solid gray;
}
.header-container {
    display: flex;
    justify-content: space-between;
}
.cursor {
    cursor: pointer;
}
* {
    font-family: 'hanna';
}
a {
    text-decoration: none;
    color: black;
}
</style>