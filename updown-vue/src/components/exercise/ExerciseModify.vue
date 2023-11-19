<template>
    <div>
        <h2>운동</h2>
        <img @click="modify" class="cursor" style="width: 2.5em;" src="../../asset/icon/save.png" alt="저장">
        <img @click="list" class="cursor" style="width: 2.5em;" src="../../asset/icon/list.png" alt="목록">
        <div>
            <label for="type">운동 종류 </label>
            <input type="text" id="type" :placeholder="props.exercise.type" v-model="newExercise.type">
        </div>
        <div>
            <label for="time">운동 시간 </label>
            <input type="number" id="time" :placeholder="props.exercise.time" v-model="newExercise.time">min
        </div>
        <div>
            <label for="calorie">소모 칼로리 </label>
            <input type="number" id="calorie" :placeholder="props.exercise.calorie" v-model="newExercise.calorie">kcal
        </div>
    </div>
</template>

<script setup>
import { ref, onMounted } from 'vue';
import { useExerciseStore } from '@/stores/exercise';

const today = new Date();
const year = today.getFullYear();
const month = ("0" + (1 + today.getMonth())).slice(-2);
const day = ("0" + today.getDate()).slice(-2);
const regDate = `${year}-${month}-${day}`;

const loginUserId = JSON.parse(localStorage.getItem("loginUser")).id;

const store = useExerciseStore();

const props = defineProps ({
    exercise: Object,
})

const newExercise = ref({
    no: props.exercise.no,
    type: '',
    time: 0,
    calorie: 0,
});

onMounted(async () => {
    await getExerciseList();
    newExercise.value.type = store.todayExerciseList.type;
    newExercise.value.time = store.todayExerciseList.time;
    newExercise.value.calorie = store.todayExerciseList.calorie;
})

const getExerciseList = async function() {
    await store.getExerciseList(loginUserId,regDate);
}

const emits = defineEmits(["list"]);

const modify = function() {
    store.modifyExercise(newExercise.value);
    emits("list")
}

const list = function() {
    emits("list")
}

</script>

<style scoped>

</style>