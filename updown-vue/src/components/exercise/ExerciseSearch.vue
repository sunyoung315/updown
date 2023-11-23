<template>
    <div class="exercise-search">
        <div class="exercise-head">
            <div class="exercise-title">운동</div>
            <img class="cursor" @click="cancel" src="../../asset/bootstrap-icon/x-lg.svg" alt="검색취소">
        </div>
        <div class="exercise-search-input">
            <input type="text" placeholder="운동명을 검색해주세요." v-model="word" @keyup.enter="search">
            <img @click="search" id="search-btn" class="cursor" src="../../asset/bootstrap-icon/search.svg" alt="검색">
        </div>
        <table class="table">
            <thead>
                <tr>
                    <th class="exercise-type">운동명</th>
                    <th class="th-exercise-met">단위체중당 에너지소비량</th>
                </tr>
            </thead>
        </table>
        <table class="table" id="exercise">
            <div class="box" style="height: 15.5rem; overflow-y: auto;">
                <tbody>
                    <tr id="exercise-content" @click="getInfo(info)" class="cursor" v-for="info in exerciseInfoList" :key="info.type">
                        <td class="exercise-type" id="exercise-value">{{ info.type }}</td>  
                        <td class="exercise-met" id="exercise-value">{{ info.met }}kcal/min/kg</td>  
                    </tr>
                </tbody>
            </div>
        </table>
    </div>
</template>

<script setup>
import { ref, computed } from 'vue';
import { useExerciseStore } from '@/stores/exercise';

const store = useExerciseStore();

const emits = defineEmits(["regist", "getInfo"]);

const exerciseInfoList = computed(() => store.exerciseInfoList);

const cancel = function() {
    emits("regist");
}

const word = ref('');

const search = async function() {
    store.searchExerciseInfo(word.value);
}

const getInfo = function(info) {
    word.value = '';
    emits("getInfo", info);
}

</script>

<style scoped>
.exercise-search {
    background-color: rgb(91, 94, 151, 0.5);
    padding: 2rem;
    border-radius: 1rem;
    margin: 1rem;
}
.exercise-title {
    font-size: xx-large;
    font-weight: bolder;
}
.exercise-head {
    display: flex;
    justify-content: space-between;
}
.exercise-search-input {
    text-align: right;
    margin-top: 0.3rem;
    margin-bottom: 0.1rem;
}
table {
    margin: 0px;
}
th {
    font-size: 0.9rem;
}
#exercise tr td {
    background-color: transparent !important;
    font-size: 0.9rem;
    text-overflow: clip;
}
.exercise-type {
    width: 12.3rem;
}
.exercise-met {
    width: 12rem;
}
#exercise-value {
    padding-left: 0.5rem;
}
#exercise-content {
    background-color: transparent !important;
    height: 2.6rem;
}
.box::-webkit-scrollbar { 
    width: 0.35rem; 
}
.box::-webkit-scrollbar-thumb { 
    background-color: #666; 
    border-radius: 1rem;
}
.box::-webkit-scrollbar-track { 
    background-color: #dcdcdc;  
    border-radius: 1rem;
    box-shadow: inset 0px 0px 5px white;
}
.table>:not(caption)>*>* {
    border-bottom-width: var(--bs-border-width);
    border-bottom-color: white;
}
input {
    height: 1.7rem;
}
#search-btn {
    width: 2rem;
    padding-left: 0.4rem;
    padding-bottom: 0.4rem;
    margin: 0;
}
</style>