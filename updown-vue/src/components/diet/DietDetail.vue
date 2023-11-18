<template>
    <div>
        <h2>{{ category }}</h2>
        <div v-if="category == '아침'">
            <div v-if="todayDietBreakFast">
                <img class="cursor" @click="modify" style="width: 3em;" src="../../asset/icon/edit.png" alt="수정">
                <img class="cursor" @click="graph" style="width: 3em;" src="../../asset/icon/graph.png" alt="그래프">
            </div>
            <div v-else>
                <img class="cursor" @click="regist" style="width: 3em;" src="../../asset/icon/add.png" alt="등록">
               
            </div>
        </div>
        <div v-if="category == '점심'">
            <div v-if="todayDietBreakFast">
                <img class="cursor" @click="modify" style="width: 3em;" src="../../asset/icon/edit.png" alt="수정">
                <img class="cursor" @click="graph" style="width: 3em;" src="../../asset/icon/graph.png" alt="그래프">
            </div>
            <div v-else>
                <img class="cursor" @click="regist" style="width: 3em;" src="../../asset/icon/add.png" alt="등록">
              
            </div>
        </div>

        <!-- 저녁, 간식도 추가하기.. 일단 아침 점심 확인해보고 -->
    </div>
</template>

<script setup>
import { computed } from 'vue';
import { useDietStore } from '@/stores/diet';

const store = useDietStore();

const props = defineProps({
    category: String,
})


// 오늘 기록한 식단
const todayDietBreakFast = computed(() => store.todayDietBreakFast);
const todayDietLunch = computed(() => store.todayDietLunch);
const todayDietDinner = computed(() => store.todayDietDinner);
const todayDietSnack = computed(() => store.todayDietSnack);

const emits = defineEmits(["modify", "regist", "graph"]);

const modify = function () {
    emits("modify");
}

const regist = function () {
    emits("regist");
}

const graph = function () {
    emits("graph");
}
</script>

<style scoped></style>