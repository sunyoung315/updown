<template>
    <div class="container">
        <h3>점심</h3>
        <h4>kcal</h4>
        <KeepAlive>
            <component :is="choose" :category="'점심'" @regist="regist" @modify="modify" @home="home" />
        </KeepAlive>
    </div>
</template>

<script setup>
import { shallowRef, onMounted } from 'vue'
import { useWeightStore } from '@/stores/weight';

import DietDetail from '@/components/diet/DietDetail.vue';
import DietModify from './DietModify.vue';
import DietUpload from './DietUpload.vue';

let choose = shallowRef(DietDetail);

const modify = function() {
    choose.value = DietModify;
}

const regist = function() {
    choose.value = DietUpload;
}

const home = function() {
    choose.value = DietDetail;
}



</script>

<style scoped>
.container {
    border: 1px solid black;
}

</style>