<template>
    <div class="container">
        <h4>운동</h4>
        <button @click="change('upload')">등록</button>
        <button @click="change('modify')">수정</button>
        <button @click="change('detail')">상세보기</button>
        <button @click="change('list')">목록보기</button>
        <KeepAlive>
            <component :is="choose"></component>
        </KeepAlive>
    </div>
</template>

<script setup>
import ExerciseDetail from '@/components/exercise/ExerciseDetail.vue';
import ExerciseModify from '@/components/exercise/ExerciseModify.vue';
import ExerciseUpload from '@/components/exercise/ExerciseUpload.vue';
import ExerciseList from '@/components/exercise/ExerciseList.vue';
import { shallowRef } from 'vue'

let choose = shallowRef(ExerciseDetail);

const change = (val) => {
    switch(val) {
    case 'detail':
        choose.value = ExerciseDetail;
        break;
    case 'upload':
        choose.value = ExerciseUpload;
        break;
    case 'modify':
        choose.value = ExerciseModify;
        break;
    case 'list':
        choose.value = ExerciseList;
        break;
    }
}

</script>

<style scoped>
.container {
    border: 1px solid black;
}

</style>