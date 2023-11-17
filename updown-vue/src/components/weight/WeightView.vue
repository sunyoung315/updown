<template>
    <div class="container">
        <KeepAlive>
            <component :is="choose" :weight="weight" @modify="modify"/>
        </KeepAlive>
    </div>
</template>

<script setup>
import WeightDetail from '@/components/weight/WeightDetail.vue';
import WeightModify from '@/components/weight/WeightModify.vue';
import WeightUpload from '@/components/weight/WeightUpload.vue';
import { shallowRef, onMounted } from 'vue'
import { useWeightStore } from '@/stores/weight';

let choose = shallowRef(WeightDetail);

const modify = function() {
    choose.value = WeightModify;
}

const regist = function() {
    choose.value = WeightUpload;
}

// const change = (val) => {
//     switch(val) {
//     case 'detail':
//         choose.value = WeightDetail;
//         break;
//     case 'upload':
//         choose.value = WeightUpload;
//         break;
//     case 'modify':
//         choose.value = WeightModify;
//         break;
//     }
// }

const today = new Date();

const year = today.getFullYear();
const month = ("0" + (1 + today.getMonth())).slice(-2);
const day = ("0" + today.getDate()).slice(-2);

const regDate = year + '-' + month + '-' + day;

const loginUserId = JSON.parse(localStorage.getItem("loginUser")).id;

const store = useWeightStore();

onMounted(() => {
    store.getWeight(loginUserId, regDate);
})

const weight = store.todayWeight;

</script>

<style scoped>
.container {
    border: 1px solid black;
}

</style>