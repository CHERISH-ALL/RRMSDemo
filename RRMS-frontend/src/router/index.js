//实现网页内容的路由信息配置
import {createRouter, createWebHistory} from 'vue-router'
import {useStore} from "@/stores";

const router = createRouter({
    history: createWebHistory(import.meta.env.BASE_URL),
    routes: [{
        path: '/',
        name: 'welcome',
        component: () => import('@/views/welcomeView.vue'),
        children: [{
            path: '',
            name: 'welcome-login',
            component: () => import('@/components/welcome/loginPage.vue'),
        }, {
            path: 'register',
            name: 'welcome-register',
            component: () => import('@/components/welcome/registerPage.vue'),
        }, {
            path: 'forget',
            name: 'welcome-forget',
            component: () => import('@/components/welcome/forgetPage.vue'),
        }]
    },
        {
            path: '/index',
            name: 'index',
            component: () => import('@/views/indexView.vue'),
            children: [{
                path: '',
                name: 'index-userIndex',
                component: () => import('@/components/function/index/userIndex.vue')
            }, {
                path: '/searchUserInformation',
                name: 'index-searchUserInformation',
                component: () => import('@/components/function/userInformation/searchUserInformation.vue')
            }, {
                path: '/changeUserInformation',
                name: 'index-changeUserInformation',
                component: () => import ('@/components/function/userInformation/changeUserInformation.vue')
            }]
        }
    ]
})


//添加路由守卫
router.beforeEach((to, from, next) => {
    const store = useStore();
    if (store.auth.user !== 'null' && to.name.startsWith('welcome-')) {
        next('/index');
    } else if (store.auth.user === 'null' && to.fullPath.startsWith('/index')) {
        next('/')
    } else if (to.matched.length === 0) {
        next('/index')
    } else {
        next();
    }
})

export default router
