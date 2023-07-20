import 'element-plus/dist/index.css'
import {createApp} from 'vue'
import App from '@/App.vue'
import router from '@/router'
import axios from "axios";
import {createPinia} from 'pinia'
import {ElementPlus} from "@element-plus/icons-vue";


const app = createApp(App)
app.use(router)
app.use(ElementPlus)
app.use(createPinia())

app.mount('#app')

axios.defaults.baseURL = 'http://localhost:2333'
