<template>
    <div>
        <TheHeaderNav/>
        <div class="home">
            <TheAside/>
            <RouterView class="home-container"/>
        </div>
        <TheFooter/>
    </div>
</template>

<script setup>
import TheHeaderNav from '@/components/common/TheHeaderNav.vue';
import TheAside from '@/components/common/TheAside.vue';
import TheFooter from '@/components/common/TheFooter.vue';

</script>

<style scoped>
.home {
    display: flex;
}
.home-container{
    padding: 20px;
}
</style>