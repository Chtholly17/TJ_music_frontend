import { createRouter, createWebHistory, RouteRecordRaw } from 'vue-router'
import HomeView from '../views/HomeView.vue'

const routes: Array<RouteRecordRaw> = [
  {
    path: '/',
    name: 'home',
    component: HomeView
  },

  {
    redirect:'/user/music_library',
    path:'/user',
    name:'user',
    component:()=>import('../views/user/UserManagementView.vue'),
    children:[
      {
        path:'music_library',
        name:'user_music_library',
        component:() =>import('../views/user/UserMusicLibraryView.vue')
      },
      {
        path:'post',
        name:'user_post',
        component:()=>import('../views/user/UserPostView.vue')
      }
    ]
  },

  // {
  //   path: '/Login',
  //   name: 'Login',
  //   // route level code-splitting
  //   // this generates a separate chunk (about.[hash].js) for this route
  //   // which is lazy-loaded when the route is visited.
  //   component: () => import(/* webpackChunkName: "about" */ '../views/LoginView.vue')
  // },
  // {
  //   path: '/Register',
  //   name: 'Register',
  //   component: () => import('../views/RegisterView.vue')
  // }
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
