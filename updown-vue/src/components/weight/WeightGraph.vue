<template>
    <div>
        <h2>체중</h2>
        <img @click="home" class="cursor" style="width: 2.5em;" src="../../asset/icon/home.png" alt="홈">
        <h4>7일간의 몸무게 변화</h4>
    </div>
</template>

<script setup>
const emit = defineEmits(["home"]);
const home = function() {
    emit("home")
}
</script>

<style scoped>

</style>