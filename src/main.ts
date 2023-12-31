import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'

import ArcoVueIcon from '@arco-design/web-vue/es/icon';

import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
import * as ElementPlusIconsVue from '@element-plus/icons-vue'
// import axios from "axios";

import mitt from 'mitt'

const app = createApp(App)
for (const [key, component] of Object.entries(ElementPlusIconsVue)) {
    app.component(key, component)
}
app.use(store).use(router)
app.use(ElementPlus)
app.use(ArcoVueIcon);
app.mount('#app')
// axios.defaults.baseURL='/api'
app.config.globalProperties.$EventBus = mitt()
