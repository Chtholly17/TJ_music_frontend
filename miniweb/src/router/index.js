import { createRouter, createWebHashHistory} from 'vue-router'
import HomeView from '../views/HomeView'
import LoginView from '../views/LoginView.vue'
import RegisterView from '../views/RegisterView.vue'

// 配置信息中需要页面的相关配置

const routes = [
  {
    path: '/',
    component: HomeView
  },
  {
    path: '/Login',
    component: LoginView
  },
  {
    path: '/Register',
    component: RegisterView
  }
]

const router = createRouter(
  {
    history: createWebHashHistory(),
    routes
  }
)

export default router;
