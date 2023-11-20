<template>
    <div class="start">
        <a>
            <img src="../asset/image/healthy-life.jpg">
        </a>
        <a>
            <RouterView class="show"/>
        </a>
    </div>
</template>

<script setup>

</script>

<style scoped>
.show{   
    width: 100%;
    padding: 30%
}
.start{
    display: flex;
}

a{
    display: flex;
    align-content: center;
    justify-content: center;
    width: 50%;
}

img{
    width: 100%
}
</style>