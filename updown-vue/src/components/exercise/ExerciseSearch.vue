<template>
    <div>
        <h2>운동</h2>
        <img class="cursor" @click="regist" style="width: 1.8em;" src="../../asset/icon/delete.png" alt="돌아가기">
        <div>
            <input type="text" placeholder="운동명을 검색해주세요." v-model="word">
            <img @click="search" class="cursor" style="width: 2.5em;" src="../../asset/icon/search.png" alt="검색">
        </div>
        <table>
            <thead>
                <th>운동명</th>
                <th>단위체중당에너지소비량</th>
            </thead>
            <tbody>
                <tr  @click="getInfo(info)" class="cursor" v-for="info in exerciseInfoList" :key="info.type">
                    <td>{{ info.type }}</td>  
                    <td>{{ info.met }}kcal/min/kg</td>  
                </tr>
            </tbody>
        </table>
    </div>
</template>

<script setup>
import { ref, computed } from 'vue';
import { useExerciseStore } from '@/stores/exercise';

const store = useExerciseStore();

const emits = defineEmits(["regist", "getInfo"]);

const regist = function() {
    emits("regist");
}

const word = ref('');

const exerciseInfoList = computed(() => store.exerciseInfoList);

const search = async function() {
    store.searchExerciseInfo(word.value);
}

const getInfo = function(info) {
    emits("getInfo", info);
}

</script>

<style scoped>

</style>