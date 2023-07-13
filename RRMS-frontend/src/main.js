import 'element-plus/dist/index.css'

import {createApp} from 'vue'
import {createPinia} from 'pinia'

import App from '@/App.vue'
import router from '@/router'
import * as ElIconModules from '@element-plus/icons'
import axios from "axios";

const app = createApp(App)

app.use(createPinia())
app.use(router)

app.mount('#app')

//  统一注册el-icon图标
for (let iconName in ElIconModules) {
    app.component(iconName, ElIconModules[iconName])
}
axios.defaults.baseURL = 'http://localhost:2333'

