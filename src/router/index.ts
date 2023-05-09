import { createRouter, createWebHistory, RouteRecordRaw } from 'vue-router'

const routes: Array<RouteRecordRaw> = [
  {
    path: '/',
    redirect:'/hello',
  },
  {
    path:"/music_player",
    name:"music_player",
    component:()=>import('@/views/music_player.vue'),
  },
  {
    redirect:'/user/music_library',
    path:'/user',
    name:'user',
    component:()=>import('@/views/user/UserManagementView.vue'),
    children:[
      {
        path:'music_library',
        name:'user_music_library',
        component:() =>import('@/views/user/UserMusicLibraryView.vue')
      },
      {
        path:'post',
        name:'user_post',
        component:()=>import('@/views/user/UserPostView.vue')
      }
    ]
  },
  {
    path:'/hello',
    name:'hello',
    component:()=>import('@/views/PrefaceView.vue'),
  },
  {
    path: '/square',
    name: 'square',
    component:()=>import('@/views/SquareView.vue'),
  },
  {
    path: '/accompaniment',
    name: 'accompaniment',
    component:()=>import('@/views/accompanimentView.vue'),
  },
  {
    path: '/detail',
    name: 'detailView',
    component:()=>import('@/views/detailView.vue')
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
