<template>
    <div>
        <h2>체중</h2>
        <div v-if="props.weight">
            <img class="cursor" @click="modify" style="width: 3em;" src="../../asset/icon/edit.png" alt="수정">
            <p>목표체중까지 {{ props.weight.nowWeight - targetWeight }}kg 남았습니다.</p>
            <h4>{{ props.weight.nowWeight }}kg</h4>
        </div>
        <div v-else>
            <img class="cursor" @click="regist" style="width: 3em;" src="../../asset/icon/add.png" alt="등록">
            <h4>0kg</h4>
        </div>
    </div>
</template>

<script setup>
import { useRouter } from 'vue-router';

const targetWeight = JSON.parse(localStorage.getItem("loginUser")).targetWeight;

const props = defineProps({
    weight: Object,
})

const emits = defineEmits(["modify", "regist"]);

const modify = function() {
    emits("modify");
}

const regist = function() {
    emits("regist");
}

</script>

<style scoped>

</style>