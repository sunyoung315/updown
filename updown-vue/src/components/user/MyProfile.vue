<template>
    <div class="myprofile">
        <div>
            <p id="title">나의 프로필</p>
            <div class="profile-container">
                <div class="img-box">
                    <img v-if="loginUser.img == true || loginUser.img !== null"
                        :src="'http://localhost:8080/upload/' + loginUser.img" alt="프로필 사진1">
                    <img v-else src="../../asset/bootstrap-icon/person-square.svg" alt="프로필 사진">
                </div>
                <br>
                <div class="modify">
                    <div class="user-name"> &nbsp;"{{ loginUser.nickname }}" 님</div>
                    <RouterLink :to="{ name: 'myprofile' }">
                        <img id="icon" class="cursor"
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
#title {
    font-size: x-large;
    font-weight: 600;
    margin-left: 1rem;
    margin-bottom: 0;
    margin-top: 0.8rem;
}
.user-name {
    font-size: x-large;
    font-weight: 600;
    padding-bottom: 0.6rem;
}
.cursor {
    width: 2rem;
    margin: 0.1rem 0.5rem 0.25rem 1rem;
}
.myprofile {
    display: flex;
    flex-direction: column;
    align-items: center;
    padding-top: 2rem;
    width: 25rem;
    margin-right: 1rem;

}

.profile-content {
    display: flex;
}

.profile-container {
    border: solid 1px rgb(197, 197, 197);
    border-radius: 0.5rem;
    padding: 1.5rem;
    width: 20rem;
    margin-top: 15px;
}
img {
    display: inline-block;
    width: 15rem;
}

.img-box {
    width: 15rem;
    height: 15rem;
    text-align: center;
    border: solid 1px rgb(197, 197, 197);
    margin: 1rem;
    display: flex;
    vertical-align: middle;
}

#info {
    font-size: large;
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