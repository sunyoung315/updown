<template>
    <div class="container">
        <h4>식단</h4>
        <button @click="change('upload')">등록</button>
        <button @click="change('modify')">수정</button>
        <button @click="change('detail')">상세보기</button>
        <KeepAlive>
            <component :is="choose"></component>
        </KeepAlive>
    </div>
</template>

<script setup>
import DietDetail from '@/components/diet/DietDetail.vue';
import DietModify from '@/components/diet/DietModify.vue';
import DietUpload from '@/components/diet/DietUpload.vue';
import { shallowRef } from 'vue'

let choose = shallowRef(DietDetail);

const change = (val) => {
    switch(val) {
    case 'detail':
        choose.value = DietDetail;
        break;
    case 'upload':
        choose.value = DietUpload;
        break;
    case 'modify':
        choose.value = DietModify;
        break;
    }
}

</script>

<style scoped>
.container {
    border: 1px solid black;
}

</style>