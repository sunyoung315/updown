<template>
    <div>
        <h2>운동</h2>
        <img @click="upload" class="cursor" style="width: 2.5em;" src="../../asset/icon/save.png" alt="저장">
        <img @click="list" class="cursor" style="width: 2.5em;" src="../../asset/icon/list.png" alt="목록">
        <div>
            <label for="type">운동 종류 </label>
            <input type="text" id="type" placeholder="오늘 한 운동을 입력하세요." v-model="newExercise.type" required>
            <img @click="search" class="cursor" style="width: 2.5em;" src="../../asset/icon/search.png" alt="검색">
        </div>
        <div>
            <label for="time">운동 시간 </label>
            <input type="number" id="time" :placeholder=0 v-model="newExercise.time">min
        </div>
        <div>
            <label for="calorie">소모 칼로리 </label>
            <input type="number" id="calorie" :placeholder=0 v-model="newExercise.calorie">kcal
        </div>
    </div>
</template>

<script setup>
import { ref, onMounted } from 'vue';
import { useExerciseStore } from '@/stores/exercise';

const loginUserId = JSON.parse(localStorage.getItem("loginUser")).id;

const store = useExerciseStore();

const newExercise = ref({
    type: '',
    time: 0,
    calorie: 0,
    userId: loginUserId,
});

const emits = defineEmits(["list"])

const list = function() {
    emits("list");
}

const upload = async function() {
    await store.uploadExercise(newExercise.value);
    emits("list");
}

</script>

<style scoped>

</style>