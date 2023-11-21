<template>
    <div class="myprofile">
        <div>
            <h3>나의 프로필</h3><br>

            <div class="profile-container">
                <div>
                    <img v-if="loginUser.img !== null" style="width: 200px;" :src="'http://localhost:8080/upload/' + loginUser.img"
                        alt="프로필 사진">
                    <img v-else style="width: 200px" src="../../asset/icon/profile-user.png" alt="프로필 사진">
                </div>
                <RouterLink :to="{ name: 'myprofile' }">
                    <img class="cursor" style="width: 27px; margin: 15px 5px 0px 5px;"
                        src="../../asset/bootstrap-icon/pencil-square.svg" alt="수정">
                </RouterLink>
                <br><br>
                <div class="profile-content">
                    <div>
                        <p>📌목표 체중 : {{ loginUser.targetWeight }}kg</p>
                        <p>📌하루 목표 운동 시간 : {{ loginUser.targetTime }}분</p>
                    </div>
                </div>
            </div>

        </div>
    </div>
</template>

<script setup>
import { computed } from 'vue';

const loginUser = computed(() => JSON.parse(localStorage.getItem("loginUser")));

</script>

<style scoped>
.myprofile {
    display: flex;
    flex-direction: column;
    align-items: center;
    padding-top: 50px;
    width: 369px;

}

.profile-content {
    display: flex;
}
.profile-container{
    border:solid 1px rgb(197, 197, 197);
    height: 380px;
    padding: 30px;
}
.cursor{
    display: inline-block;
    float: right;
}
</style>