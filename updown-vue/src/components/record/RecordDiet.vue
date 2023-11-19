<template>
    <div>
        <h3>식단</h3>
        <table v-if="props.dietRecord.length">
            <thead>
                <tr>
                    <th>구분</th>
                    <th>음식명</th>
                    <th>칼로리</th>
                    <th>사진</th>
                </tr>
            </thead>
            <tbody v-for="diet in dietRecord" :key=diet.no>
                <tr>
                    <td>{{ diet.category }}</td>
                    <td>{{ diet.food }}</td>
                    <td>{{ diet.calorie }}kcal</td>
                    <td v-if="diet.img"><img :src="`http://localhost:8080/upload/${diet.img}`" alt="식단 사진" style="width: 5em;"></td>
                </tr>
            </tbody>
        </table>
        <div v-else>조회된 기록이 없습니다.</div>
    </div>
</template>

<script setup>
const props = defineProps({
    dietRecord: Object,
})
</script>

<style scoped>

</style>