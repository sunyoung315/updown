<template>
    <div>
        <h3>나의 프로필</h3>
        <img v-if="loginUser.img!==null" style="width: 3em;"  :src="'http://localhost:8080/upload/' + loginUser.img" alt="프로필 사진">
        <img v-else style="width: 15em" src="../../asset/icon/profile-user.png" alt="프로필 사진">
        <div class="profile-content">
            <div>
                <p>📌목표 체중 : {{ loginUser.targetWeight }}kg</p>
                <p>📌하루 목표 운동 시간 : {{ loginUser.targetTime }}분</p>
            </div>
            <RouterLink :to="{name: 'myprofile'}">
                <img style="width: 3em;" src="../../asset/icon/edit.png" alt="수정">
            </RouterLink>
        </div>
    </div>
</template>

<script setup>
const loginUser = JSON.parse(localStorage.getItem("loginUser"));

</script>

<style scoped>
.profile-content {
    display: flex;
}
</style>