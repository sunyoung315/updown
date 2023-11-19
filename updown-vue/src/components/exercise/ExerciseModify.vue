<template>
    <div>
        <h2>운동</h2>
        <img @click="modify" class="cursor" style="width: 2.5em;" src="../../asset/icon/save.png" alt="저장">
        <img @click="list" class="cursor" style="width: 2.5em;" src="../../asset/icon/list.png" alt="목록">
        <div>
            <label for="type">운동 종류 : </label>
            {{ props.exercise.type }}
        </div>
        <div>
            <label for="time">운동 시간 </label>
            <input type="number" id="time" v-model="newExercise.time">min
        </div>
        <div>
            <label for="calorie">소모 칼로리 </label>
            {{ (exerciseInfo.met * (3.5 * weight * newExercise.time) / 1000 * 5).toFixed(1) }} kcal
        </div>
    </div>
</template>

<script setup>
import { ref, computed, onMounted } from 'vue';
import { useExerciseStore } from '@/stores/exercise';
import { useWeightStore } from '@/stores/weight';

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

const weightStore = useWeightStore();
const weight = weightStore.todayWeight.nowWeight;

onMounted(async () => {
    await store.getExerciseList(loginUserId,regDate);
    await store.searchExerciseInfoDetail(props.exercise.type);
})

const exerciseInfo = computed(() => store.exerciseInfo);

const emits = defineEmits(["list"]);

const modify = function() {
    newExercise.value.type = props.exercise.type;
    newExercise.value.calorie = (exerciseInfo.value.met * (3.5 * weight * newExercise.value.time) / 1000 * 5).toFixed(1);
    console.log(newExercise.value)
    store.modifyExercise(newExercise.value);
    emits("list")
}

const list = function() {
    emits("list")
}

</script>

<style scoped>

</style>