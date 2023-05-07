import { createRouter, createWebHistory, RouteRecordRaw } from 'vue-router'
import music_player from "@/views/music_player.vue";
import music_square from "@/views/music_square.vue";

const routes: Array<RouteRecordRaw> = [
  {
    path: '/',
    redirect:'/hello',
  },
  {

    path:"/music_player",
    name:"music_player",
    component:music_player
  },
  {

    path:"/music_square",
    name:"music_square",
    component:music_square
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
      },
      {
        path:'fan_list',
        name:'user_fan_list',
        component:()=>import('../components/user/FanList.vue')
      },
      {
        path:'follow_list',
        name:'user_follow_list',
        component:()=>import('../components/user/FollowList.vue')
      }
    ]
  },
  {
    path:'/hello',
    name:'hello',
    component:()=>import('../views/PrefaceView.vue'),
  },
  {
    path: '/square',
    name: 'square',
    component:()=>import('../views/SquareView.vue'),
  },
  {
    path: '/accompaniment',
    name: 'accompaniment',
    component:()=>import('../views/accompanimentView.vue'),
  }


  // {
  //   path: '/Login',
  //   name: 'Login',
  //   // route level code-splitting
  //   // this generates a separate chunk (about.[hash].js) for this route
  //   // which is lazy-loaded when the route is visited.
  //   component: () => import(/* webpackChunkName: "about" */ '../views/PrefaceView.vue')
  // },
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
