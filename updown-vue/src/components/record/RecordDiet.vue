<template>
    <div>
        <p class="title">식단</p>
        <table class="table" id="diet" v-if="props.dietRecord.length">
            <thead class="table-light">
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
                    <td v-else="diet.img" ><i class="bi bi-card-image"></i></td>
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
.title {
    font-size: x-large;
    font-weight: bold;
    padding-left: 0.5rem;
}
th{
    background-color: rgb(83,120,77, 0.3);
}

#diet{
    text-align: center; 
    font-size: large; 
    width: 43em;
}

.bi-card-image{
    color:rgb(199, 199, 199);
}
</style>