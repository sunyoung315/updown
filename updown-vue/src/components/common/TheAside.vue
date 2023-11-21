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
    border: 1px solid rgb(199, 199, 199);
    width: 500px;
}
</style>