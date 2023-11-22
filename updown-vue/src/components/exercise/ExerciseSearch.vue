<template>
    <div class="exercise-search">
        <div class="exercise-head">
            <div class="exercise-title">운동</div>
            <img class="cursor" @click="cancel" style="width: 43px; margin: 0px 5px 0px 5px;" src="../../asset/bootstrap-icon/x-lg.svg" alt="검색취소">
        </div>
        <div class="exercise-search-input">
            <input type="text" placeholder="운동명을 검색해주세요." v-model="word" @keyup.enter="search">
            <img @click="search" class="cursor" style="width: 2.5em; margin-left: 5px;" src="../../asset/icon/search.png" alt="검색">
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
            <div class="box" style="height: 250px; overflow-y: auto;">
                <tbody>
                    <tr id="exercise-content" @click="getInfo(info)" class="cursor" v-for="info in exerciseInfoList" :key="info.type">
                        <td class="exercise-type">{{ info.type }}</td>  
                        <td class="exercise-met">{{ info.met }}kcal/min/kg</td>  
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
    padding: 40px;
    border-radius: 20px;
}
.exercise-title {
    font-size: 50px;
}
.exercise-head {
    display: flex;
    justify-content: space-between;
}
.exercise-search-input {
    text-align: right;
    margin-top: 15px;
    margin-bottom: 10px;
}
table {
    margin: 0px;
}
th {
    font-size: 18px;
    /* width: 515px; */
}
#exercise tr td {
    background-color: transparent !important;
    font-size: 18px;
    text-overflow: clip;
}
.exercise-type {
    width: 250px;
    padding-left: 10px;
    padding-right: 10px;
}
.exercise-met {
    width: 300px;
    padding-left: 25px;
    padding-right: 10px;
}
#exercise-content {
    background-color: transparent !important;
    height: 55px;
}
.box::-webkit-scrollbar { 
    width: 5px; 
}
.box::-webkit-scrollbar-thumb { 
    background-color: #666; 
    border-radius: 10px;
}
.box::-webkit-scrollbar-track { 
    background-color: #dcdcdc;  
    border-radius: 10px;
    box-shadow: inset 0px 0px 5px white;
}
.table>:not(caption)>*>* {
    border-bottom-width: var(--bs-border-width);
    border-bottom-color: white;
}

</style>