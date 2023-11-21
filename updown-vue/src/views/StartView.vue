<template>
    <div class="start">
        <a id="img">
            <img src="../asset/image/healthy-life.jpg">
        </a>
        <a id="show">
            <RouterView class="show" />
        </a>
    </div>
</template>

<script setup>

</script>

<style scoped>
.show {
    padding: 160px;
    width: 650px;
    margin-left: 35px;
}

.start {
    display: flex;
    justify-content: center;
    height: 931px;
}

#show {
    display: flex;
    justify-content: left;
    padding-top: 80px;
    height: 931px;
    width: 750px;

}


img {
    width: 800px;
    height: 931px;

}
</style>