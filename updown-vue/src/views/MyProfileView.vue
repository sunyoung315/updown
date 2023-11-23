<template>
    <div class="myprofile">
        <MyProfileModify/>
    </div>
</template>

<script setup>
import MyProfileModify from '@/components/user/MyProfileModify.vue';
</script>

<style scoped>
.myprofile{
    margin-top: 20px;
    margin-left: 20px;
    padding-bottom: 80px;
    padding-right: 18px;
    background-color: white;
    border-radius: 20px;
    border: 1px solid rgb(199, 199, 199);
}
</style>