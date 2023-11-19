<template>
    <div class="container">
        <KeepAlive>
            <component :is="choose" @regist="regist" @modify="modify" @home="home" @graph="graph"/>
        </KeepAlive>
    </div>
</template>

<script setup>
import { shallowRef, onMounted } from 'vue';
import { useWeightStore } from '@/stores/weight';

import WeightDetail from '@/components/weight/WeightDetail.vue';
import WeightModify from '@/components/weight/WeightModify.vue';
import WeightUpload from '@/components/weight/WeightUpload.vue';
import WeightGraph from '@/components/weight/WeightGraph.vue';

let choose = shallowRef(WeightDetail);

const modify = function() {
    choose.value = WeightModify;
}

const regist = function() {
    choose.value = WeightUpload;
}

const home = function() {
    choose.value = WeightDetail;
}

const graph = function() {
    choose.value = WeightGraph;
}

const today = new Date();
const year = today.getFullYear();
const month = ("0" + (1 + today.getMonth())).slice(-2);
const day = ("0" + today.getDate()).slice(-2);
const regDate = `${year}-${month}-${day}`;

const loginUserId = JSON.parse(localStorage.getItem("loginUser")).id;

const store = useWeightStore();

onMounted(async () => {
    await store.getWeight(loginUserId, regDate);
})

</script>

<style scoped>
.container {
    border: 1px solid black;
}

</style>