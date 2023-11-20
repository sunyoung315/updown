<template>
    <div class="exercise-upload">
        <h2>운동</h2>
        <img @click="upload" class="cursor" style="width: 2.5em;" src="../../asset/icon/save.png" alt="저장">
        <img @click="list" class="cursor" style="width: 2.5em;" src="../../asset/icon/list.png" alt="목록">
        <div>
            <label for="type">운동 종류</label>
            <span v-if="props.info.type">{{ ' : ' + props.info.type }}</span>
            <img @click="search" class="cursor" style="width: 2em;" src="../../asset/icon/search.png" alt="검색">
        </div>
        <div>
            <label for="time">운동 시간 : </label>
            <input type="number" id="time" v-model="newExercise.time">min
        </div>
        <div v-if="props.info.met">
            <label for="calorie">소모 칼로리 </label>
            <div v-if="weight">{{ (props.info.met * (3.5 * weight * newExercise.time) / 1000 * 5).toFixed(1) }} kcal</div>
            <div v-else>
                {{ (props.info.met * (3.5 * 50 * newExercise.time) / 1000 * 5).toFixed(1) }} kcal
                <br>
                (체중이 입력되지 않아 50kg 기준으로 계산됩니다.)
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
    newExercise.value.calorie = (props.info.met * (3.5 * weight.value * newExercise.value.time) / 1000 * 5).toFixed(1);
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
    padding: 2em;
    border-radius: 20px;
}
</style>