import { createRouter, createWebHistory } from 'vue-router'

import StartView from '@/views/StartView.vue'
import HomeView from '@/views/HomeView.vue'
import TodayView from '@/views/TodayView.vue'
import RecordView from '@/views/RecordView.vue'
import MyProfileView from '@/views/MyProfileView.vue'

import Login from '@/components/user/Login.vue'
import SignUp from '@/components/user/SignUp.vue'
import fileUploadTest from '@/components/user/fileUploadTest.vue'


const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '',
      name: 'start',
      component: StartView,
      children: [
        {
          path: '',
          name: 'login',
          component: Login,
        },
        {
          path: '/signup',
          name: 'signup',
          component: SignUp,
        }
      ]
    },
    {
      path: '/home',
      name: 'home',
      component: HomeView,
      children: [
        {
          path: '/today',
          name: 'today',
          component: TodayView,
        },
        {
          path: '/record',
          name: 'record',
          component: RecordView
        },
        {
          path: '/myprofile',
          name: 'myprofile',
          component: MyProfileView,
        },
      ]
    },
    {
      path: '/test',
      name: 'test',
      component: fileUploadTest,
    },
   
  ]
})

export default router
