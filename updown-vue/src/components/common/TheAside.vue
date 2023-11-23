<template>
    <aside>
        <MyProfile/>
    </aside>
</template>

<script setup>
import MyProfile from '@/components/user/MyProfile.vue'
</script>

<style scoped>
aside {
    display: flex;
    justify-content: right;
    border-right: 0.1rem solid rgb(199, 199, 199);
    width: 30%;
    background-color: white;
}
</style>