<template>
    <div>
        <h3>체중</h3>
        <div id="weight" v-if="weightRecord.nowWeight">{{ props.weightRecord.nowWeight }}kg</div>
        <div v-else>조회된 기록이 없습니다.</div>
    </div>
</template>

<script setup>
const props = defineProps({
    weightRecord: Object,
})
</script>


<style scoped>
#weight {
    width: 8rem;
    text-align: center;
    background-color: rgb(193, 96, 96, 0.3);
    border: none;
    font-size: x-large;
    padding: 1rem;
    border-radius: 15px;
}
</style>