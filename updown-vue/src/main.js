import "bootstrap/dist/css/bootstrap.min.css"
import "bootstrap-icons/font/bootstrap-icons.css"
import VCalendar from 'v-calendar';
import 'v-calendar/dist/style.css';
import VueApexCharts from "vue3-apexcharts";

import { createApp } from 'vue'
import { createPinia } from 'pinia'

import App from './App.vue'
import router from './router'

const app = createApp(App)

app.use(createPinia())
app.use(router)
app.use(VCalendar, {})
app.use(VueApexCharts);

app.mount('#app')

import "bootstrap/dist/js/bootstrap.js"
