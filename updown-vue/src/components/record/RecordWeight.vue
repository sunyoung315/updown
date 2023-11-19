<template>
    <div>
        <h3>체중</h3>
        <div v-if="weightRecord.nowWeight">{{ props.weightRecord.nowWeight }}kg</div>
        <div v-else>조회된 기록이 없습니다.</div>
    </div>
</template>

<script setup>
const props = defineProps({
    weightRecord: Object,
})
</script>

<style scoped>

</style>