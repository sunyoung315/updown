<template>
    <div class="exercise-upload">
        <div class="exercise-head">
            <div class="exercise-title">운동</div>
            <div>
                <img @click="upload" class="cursor" style="width: 43px; margin: 18px 8px 0px 5px;" src="../../asset/bootstrap-icon/save.svg" alt="저장">
                <img @click="list" class="cursor" style="width: 43px; margin: 18px 5px 0px 8px;" src="../../asset/bootstrap-icon/table.svg" alt="목록">
            </div>
        </div>
        <div class="exercise-upload-box">
            <div>
                <label for="type">운동 종류 : </label>
                <img @click="search" class="cursor" style="width: 43px; margin: 0px 5px 20px 8px;" src="../../asset/bootstrap-icon/search.svg" alt="검색"><br>
                <span class="exercise-value" v-if="props.info.type">{{ ' ' + props.info.type }}</span>
            </div>
            <div>
                <label for="time">운동 시간 : </label><br>
                <div class="exercise-value"><input type="number" id="time" v-model="newExercise.time">min</div>
            </div>
            <div v-if="props.info.met">
                <label for="calorie">소모 칼로리 : </label><br>
                <div class="exercise-value">
                    <div v-if="weight">{{ (props.info.met * (3.5 * weight * newExercise.time) / 1000 * 5).toFixed(1) }} kcal</div>
                    <div v-else>
                        {{ (props.info.met * (3.5 * 50 * newExercise.time) / 1000 * 5).toFixed(1) }} kcal
                        <br>
                        <p class="cal-desc">(체중이 입력되지 않아 50kg 기준으로 계산됩니다.)</p>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script setup>
import { ref, onMounted, computed } from 'vue';
import { useExerciseStore } from '@/stores/exercise';
import { useWeightStore } from '@/stores/weight';

const today = new Date();
const year = today.getFullYear();
const month = ("0" + (1 + today.getMonth())).slice(-2);
const day = ("0" + today.getDate()).slice(-2);
const regDate = `${year}-${month}-${day}`;

const loginUserId = JSON.parse(localStorage.getItem("loginUser")).id;

const store = useExerciseStore();

const props = defineProps({
    info: Object,
})

const newExercise = ref({
    type: '',
    time: 0,
    calorie: 0,
    userId: loginUserId,
});

const weightStore = useWeightStore();
const weight = computed(() => weightStore.todayWeight.nowWeight);

const emits = defineEmits(["list", "search"])

const list = function() {
    emits("list");
    newExercise.time = 0;
}

onMounted(async () => {
    await store.getExerciseList(loginUserId,regDate);
})

const upload = async function() {
    newExercise.value.type = props.info.type;
    if(props.info.weight)
        newExercise.value.calorie = (props.info.met * (3.5 * weight.value * newExercise.value.time) / 1000 * 5).toFixed(1);
    newExercise.value.calorie = (props.info.met * (3.5 * 50 * newExercise.value.time) / 1000 * 5).toFixed(1);
    await store.uploadExercise(newExercise.value);
    await store.getExerciseList(loginUserId, regDate);
    emits("list");
}

const search = async function() {
    emits("search");
}

</script>

<style scoped>
.exercise-upload {
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
.exercise-upload-box {
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
.cal-desc {
    font-size: 18px;
    color: rgb(100, 100, 100);
}
</style>