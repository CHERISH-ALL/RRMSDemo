//定义数据仓库
import {reactive} from 'vue'
import {defineStore} from 'pinia'

export const useStore = defineStore('store', () => {
    const auth = reactive({
        user: ''
    })
    return {auth}
})