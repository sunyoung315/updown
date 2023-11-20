<template>
    <div class="weight-graph">
        <div class="weight-head">
            <div class="weight-title">체중</div>
            <img @click="home" class="cursor" style="width: 49px; margin: 15px 5px 5px 5px;" src="../../asset/bootstrap-icon/house.svg" alt="홈으로">
        </div>
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
.weight-graph {
    background-color: rgb(193, 96, 96, 0.5);
    padding: 40px;
    border-radius: 20px;
}
.weight-title {
    font-size: 50px;
}
.weight-head {
    display: flex;
    justify-content: space-between;
}
</style>