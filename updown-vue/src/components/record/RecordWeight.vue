<template>
    <div>
        <p class="title">체중</p>
        <table class="table" id="weight" v-if="weightRecord.nowWeight" >
            <thead class="table-light">
                <tr>
                    <th>체중</th>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <td>{{ props.weightRecord.nowWeight }}kg</td>
                </tr>
            </tbody>
        </table>
        <div v-else>조회된 기록이 없습니다.</div>

    </div>
</template>

<script setup>
const props = defineProps({
    weightRecord: Object,
})
</script>


<style scoped>
.title {
    font-size: x-large;
    font-weight: bold;
    padding-left: 0.5rem;
}

th{
    background-color: rgb(193,96,96, 0.3)
}

#weight{
    text-align: center; font-size: large; width: 11em;
}

</style>