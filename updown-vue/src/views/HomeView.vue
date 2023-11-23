<template>
    <div>
        <TheHeaderNav/>
        <div class="home">
            <TheAside/>
            <div>
                <div class="home-container">
                    <RouterView/>
                </div>
                <TheFooter/>
            </div>
        </div>
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
    background-color: rgb(235, 235, 235);
}
.home-container {
    min-height: 100vh;
}
</style>