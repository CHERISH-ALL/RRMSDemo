//实现网页内容的路由信息配置
import {createRouter, createWebHistory} from 'vue-router'

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
  }, {
    path: '/index',
    name: 'index',
    component: () => import('@/views/indexView.vue')
  }
  ]
})

export default router
