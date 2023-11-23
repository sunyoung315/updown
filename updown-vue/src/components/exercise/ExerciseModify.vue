<template>
    <div class="exercise-modify">
        <div class="exercise-head">
            <div class="exercise-title">운동</div>
            <div>
                <img @click="modify" class="cursor" src="../../asset/bootstrap-icon/save.svg" alt="저장">
                <img @click="list" id="list-btn" class="cursor" src="../../asset/bootstrap-icon/table.svg" alt="목록">
            </div>
        </div>
        <div class="exercise-modify-box">
            <div>
                <label for="type">운동 종류 : </label><br>
                <div class="exercise-fix">{{ props.exercise.type }}</div>
            </div>
            <div>
                <label for="time">운동 시간 : </label><br>
                <div class="exercise-value"><input type="number" id="time" v-model="newExercise.time">min</div>
                <p v-if="!newExercise.time">운동 시간을 등록해주세요.</p>
            </div>
            <div>
                <label for="calorie">소모 칼로리 : </label><br>
                <div v-if="!exerciseInfo.met">
                    <div class="exercise-value"><input type="number" id="time" v-model="newExercise.calorie">kcal</div>
                </div>
                <div v-else>
                    <div v-if="weight">
                        <div class="exercise-fix">{{ (exerciseInfo.met * (3.5 * weight * newExercise.time) / 1000 * 5).toFixed(1) }} kcal</div>
                    </div>
                    <div v-else>
                        <div class="exercise-fix">{{ (exerciseInfo.met * (3.5 * 50 * newExercise.time) / 1000 * 5).toFixed(1) }} kcal</div>
                    </div>
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
    type: props.exercise.type,
    time: props.exercise.time,
    calorie: props.exercise.calorie,
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
    if(newExercise.value.time <= 0 || !newExercise.value.time) return;
    newExercise.value.type = props.exercise.type;
    if(exerciseInfo.value.met) {
        if(weight) {
            newExercise.value.calorie = (exerciseInfo.value.met * (3.5 * weight * newExercise.value.time) / 1000 * 5).toFixed(1);
        }else {
            newExercise.value.calorie = (exerciseInfo.value.met * (3.5 * 50 * newExercise.value.time) / 1000 * 5).toFixed(1);
        }
    }
    store.modifyExercise(newExercise.value);
    props.exercise.type = ''
    props.exercise.time = 0;
    props.exercise.calorie = 0;
    props.exercise.no = 0;
    emits("list")
}

const list = function() {
    emits("list")
}

</script>

<style scoped>
.exercise-modify {
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
.exercise-modify-box {
    margin-top: 2rem;
    margin-left: 2rem;
    margin-right: 2rem;
}
label {
    font-size: 1rem;
    font-weight: 600;
    margin-top: 0.9rem;
}
input {
    width: 14rem;
    height: 2.5rem;
    font-size: 0.9rem;
}
.exercise-value {
    font-size: 1.5rem;
}
.exercise-fix {
    font-size: 1.5rem;
    font-weight: 600;
}
p{   
    font-size: 0.7rem;
    color: rgb(43, 45, 84);
    margin-top: 0.15rem;
    margin-bottom: 0;
}
.cursor {
    width: 2rem;
    margin: 0.5rem 0 0.25rem 1rem;
}
#list-btn {
    width: 2.1rem;
    margin: 0.5rem 0 0.25rem 1rem;
}
#search-btn {
    width: 2rem;
    padding-left: 0.5rem;
    padding-bottom: 0.5rem;
    margin: 0;
}
</style>