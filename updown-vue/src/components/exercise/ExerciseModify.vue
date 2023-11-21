<template>
    <div class="exercise-modify">
        <div class="exercise-head">
            <div class="exercise-title">운동</div>
            <div>
                <img @click="modify" class="cursor" style="width: 43px; margin: 18px 8px 0px 5px;" src="../../asset/bootstrap-icon/save.svg" alt="저장">
                <img @click="list" class="cursor" style="width: 43px; margin: 18px 5px 0px 8px;" src="../../asset/bootstrap-icon/table.svg" alt="목록">
            </div>
        </div>
        <div class="exercise-modify-box">
            <div>
                <label for="type">운동 종류 : </label><br>
                <div class="exercise-value">{{ props.exercise.type }}</div>
            </div>
            <div>
                <label for="time">운동 시간 : </label><br>
                <div class="exercise-value"><input type="number" id="time" v-model="newExercise.time">min</div>
            </div>
            <div>
                <label for="calorie">소모 칼로리 : </label><br>
                <div v-if="weight">
                    <div class="exercise-value">{{ (exerciseInfo.met * (3.5 * weight * newExercise.time) / 1000 * 5).toFixed(1) }} kcal</div>
                </div>
                <div v-else>
                    <div class="exercise-value">{{ (exerciseInfo.met * (3.5 * 50 * newExercise.time) / 1000 * 5).toFixed(1) }} kcal</div>
                </div>
            </div>
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
    time: props.exercise.time,
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
    if(weight)
        newExercise.value.calorie = (exerciseInfo.value.met * (3.5 * weight * newExercise.value.time) / 1000 * 5).toFixed(1);
    newExercise.value.calorie = (exerciseInfo.value.met * (3.5 * 50 * newExercise.value.time) / 1000 * 5).toFixed(1);
    store.modifyExercise(newExercise.value);
    emits("list")
}

const list = function() {
    emits("list")
}

</script>

<style scoped>
.exercise-modify {
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
.exercise-modify-box {
    margin-top: 35px;
    margin-left: 20px;
    margin-right: 20px;
}
label {
    font-size: 25px;
    margin-top: 25px;
}
input {
    width: 150px;
}
.exercise-value {
    font-size: 35px;
}
</style>