<template>
    <div class="myprofile">
        <div>
            <h3>&nbsp;&nbsp;나의 프로필</h3>
            <div class="profile-container">
                <div class="img-box">
                    <img v-if="loginUser.img == true || loginUser.img !== null" style="width: 258px;"
                        :src="'http://localhost:8080/upload/' + loginUser.img" alt="프로필 사진1">
                    <img v-else style="width: 258px" src="../../asset/icon/free-icon-strength-908893.png" alt="프로필 사진">
                </div>
                <br>
                <div class="modify">
                    <div style="font-size: 30px;"> &nbsp;"{{ loginUser.nickname }}" 님</div>
                    <RouterLink :to="{ name: 'myprofile' }">
                        <img id="icon" class="cursor" style="width: 40px; margin: 15px 5px 0px 5px;"
                            src="../../asset/bootstrap-icon/pencil-square.svg" alt="수정">
                    </RouterLink>
                </div>
                <div class="profile-content">
                    <div id="info">
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

.profile-container {
    border: solid 1px rgb(197, 197, 197);
    padding: 25px;
    width: 320px;
    margin-top: 15px;
}


.img-box {
    width: 270px;
    text-align: center;
    border: solid 1px rgb(197, 197, 197);
}

#info {
    font-size: 19px;
    margin-top: 8px;
}

.modify {
    display: flex;
    justify-content: space-between;
    align-items: center;
}   

a {
    display: flex;
    justify-content: center;
    padding-bottom: 15px;
}
</style>