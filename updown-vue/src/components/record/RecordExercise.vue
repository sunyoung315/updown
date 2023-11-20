<template>
    <div>
        <h3>운동</h3>
        <table class="table" id="exercise" v-if="props.exerciseRecord.length">
            <thead class="table-light">
                <tr>
                    <th>운동명</th>
                    <th>운동 시간</th>
                    <th>소모 칼로리</th>
                </tr>
            </thead>
            <tbody>
                <tr v-for="exercise in props.exerciseRecord" :key="exercise.no">
                    <td>{{ exercise.type }}</td>
                    <td>{{ exercise.time }}min</td>
                    <td>{{ exercise.calorie }}kcal</td>
                </tr>
            </tbody>
        </table>
        <div v-else>조회된 기록이 없습니다.</div>
    </div>
</template>

<script setup>
const props = defineProps({
    exerciseRecord: Object,
})
</script>

<style scoped>

th{
    background-color: rgb(91,94,151, 0.3)
}

#exercise{
    text-align: center; font-size: large; width: 43em;
}

</style>