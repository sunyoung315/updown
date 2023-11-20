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
    width: 800px;
    height: 745.775px;
    padding: 30%
}
.start{
    display: flex;
}

a{
    display: flex;
    align-content: center;
    justify-content: center;
    width: 683.65px;
    
}

img{
    width: 683.65px
}
</style>