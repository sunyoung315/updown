<template>
    <div class="container">
        <KeepAlive>
            <component :is="choose" :category="'저녁'" @regist="regist" @modify="modify" :diet="diet" @home="home" @remove="remove"/>
        </KeepAlive>
    </div>
</template>

<script setup>
import { shallowRef, ref } from 'vue'
import DietDetail from '@/components/diet/DietDetail.vue';
import DietModify from './DietModify.vue';
import DietUpload from './DietUpload.vue';
import { useDietStore } from '@/stores/diet';

const store = useDietStore();
let choose = shallowRef(DietDetail);

let diet = ref({});

const modify = function(d) {
    diet.value=d;
    choose.value = DietModify;
    console.log(diet)
}


const regist = function() {
    choose.value = DietUpload;
}

const home = function() {
    choose.value = DietDetail;
}

const remove = function(diet) {
    store.remove(diet);
}


</script>

<style scoped>
.container {
    display: inline-block;
    width: 50%;
    border-radius: 10px;
    background-color: white;
    margin: 15px 15px 15px 0px;
    padding: 15px;
    height: 380px;
}

</style>