<template>
    <div class="container">
        <KeepAlive>
            <component :is="choose" @regist="regist" @modify="modify" @home="home" @list="list"></component>
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

const modify = function() {
    choose.value = ExerciseModify;
}

const regist = function() {
    choose.value = ExerciseUpload;
}

const home = function() {
    choose.value = ExerciseDetail;
}

const list = function() {
    choose.value = ExerciseList;
}



</script>

<style scoped>
.container {
    border: 1px solid black;
}

</style>