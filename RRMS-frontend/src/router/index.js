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
            }, {
                path: '/searchStudentInformationByObject',
                name: 'index-searchStudentInformationByObject',
                component: () => import('@/components/function/student/searchStudentInformationByObject.vue')
            }, {
                path: '/searchStudentInformationByStudent',
                name: 'index-searchStudentInformationByStudent',
                component: () => import('@/components/function/student/searchStudentInformationByStudent.vue')
            }, {
                path: '/changeStudentInformation',
                name: 'index-changeStudentInformation',
                component: () => import('@/components/function/student/changeStudentInformation.vue')
            }, {
                path: '/searchRecordByLaboratory',
                name: 'index-searchRecordByLaboratory',
                component: () => import('@/components/function/record/searchRecordByLaboratory.vue')
            }, {
                path: '/searchRecordByStudent',
                name: 'index-searchRecordByStudent',
                component: () => import('@/components/function/record/searchRecordByStudent.vue')
            }, {
                path: '/submitEquipmentApplication',
                name: 'index-submitEquipmentApplication',
                component: () => import('@/components/function/application/submit/submitEquipmentApplication.vue')
            }, {
                path: '/handleEquipmentApplication',
                name: 'index-handleEquipmentApplication',
                component: () => import('@/components/function/application/handle/handleEquipmentApplication.vue')
            }, {
                path: '/submitProjectApplication',
                name: 'index-submitProjectApplication',
                component: () => import('@/components/function/application/submit/submitProjectApplication')
            }, {
                path: '/handleProjectApplication',
                name: 'index-handleProjectApplication',
                component: () => import('@/components/function/application/handle/handleProjectApplication.vue')
            }, {
                path: '/searchAchievements',
                name: 'index-searchAchievements',
                component: () => import('@/components/function/achievements/searchAchievements.vue')
            }, {
                path: '/changeAchievements',
                name: 'index-changeAchievements',
                component: () => import('@/components/function/achievements/changeAchievements.vue')
            }, {
                path: '/changeProject',
                name: 'index-changeProject',
                component: () => import('@/components/function/project/changeProject.vue')
            }, {
                path: '/searchProject',
                name: 'index-searchProject',
                component: () => import('@/components/function/project/searchProject.vue')
            }, {
                path: '/searchTeacherByProject',
                name: 'index-searchTeacherByProject',
                component: () => import('@/components/function/teacher/searchTeacherByProject.vue')
            }, {
                path: '/searchTeacherByTeacher',
                name: 'index-searchTeacherByTeacher',
                component: () => import('@/components/function/teacher/searchTeacherByTeacher.vue')
            }, {
                path: '/changeTeacherInformation',
                name: 'index-changeTeacherInformation',
                component: () => import('@/components/function/teacher/changeTeacherInformation.vue')
            }, {
                path: '/searchEquipment',
                name: 'index-searchEquipment',
                component: () => import('@/components/function/equipment/searchEquipment.vue')
            }, {
                path: '/changeEquipment',
                name: 'index-changeEquipment',
                component: () => import('@/components/function/equipment/changeEquipment.vue')
            }, {
                path: '/searchLaboratory',
                name: 'index-searchLaboratory',
                component: () => import('@/components/function/laboratory/searchLaboratory.vue')
            }, {
                path: '/changeLaboratory',
                name: 'index-changeLaboratory',
                component: () => import('@/components/function/laboratory/changeLaboratory.vue')
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
