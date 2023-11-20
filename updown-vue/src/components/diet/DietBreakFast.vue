<template>
    <div class="container">
        <KeepAlive>
            <component :is="choose" :category="'아침'" @regist="regist" @modify="modify" :diet="diet" @home="home"  @remove="remove" />
        </KeepAlive>
    </div>
</template>

<script setup>
import { shallowRef, onMounted, ref } from 'vue'
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
    border: 1px solid black;
}

</style>