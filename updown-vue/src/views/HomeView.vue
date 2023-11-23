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

@import url('https://fonts.googleapis.com/css2?family=IBM+Plex+Sans:ital,wght@0,100;0,200;0,300;0,400;0,500;0,600;0,700;1,100;1,200;1,300;1,400;1,500;1,600;1,700&display=swap');
body {
    font-family: 'IBM Plex Sans', sans-serif;
}
.home {
    display: flex;
    flex-direction: row;
    background-color: rgb(235, 235, 235);
}
.home-container {
    display: flex;
    flex-wrap: wrap; 
    flex-direction: row;
    justify-content: space-around;
    align-items: center;
}
</style>