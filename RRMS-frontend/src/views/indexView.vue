<!--登录完成的页面设计-->
<template>
  <div style="width :100vw; height: 100vh; overflow:hidden">
    <div style="width: 100vw;height: 10vh;background-color: #545c64">
      <div style="float:left;width: 25vw;height: 10vh">
        <el-image fit="cover" src="./src/assets/login/gxunLogo.png"
                  style="max-width: 100%;height:100%;object-fit: cover"/>
      </div>

      <div style="float: left;width: 40vw;height: 10vh;"></div>

      <div style="float:left;width: 35vw;height: 10vh;">
        <div style="margin-left: 0.5vw;margin-top:1vh;width: 34vw;height: 8vh">
          <div style="float:left;height: 8vh;width: 8vh;align-items: center;justify-content:center;display: flex;">
            <el-avatar :src="circleUrl" size="large"/>
          </div>
          <div style="float:left;margin-left: 1vw;width:29vw;height:8vh;text-align: center">
            <div
                style="float:left;margin-top:2vh;align-items: center;justify-content:center;display: flex;font-size: 3vh">
              欢迎{{ store.auth.user.username }}进入RRMS
            </div>
            <div
                style="margin-left:1vw;float:left;margin-top:2vh;align-items: center;justify-content:center;display: flex">
              <el-button type="danger" @click="logout()">退出登录</el-button>
            </div>
          </div>
        </div>
      </div>

    </div>
    <div style="width: 100vw;height: 90vh">
      <div style="float:left;width: 15vw;height: 90vh">
        <el-menu
            active-text-color="#ffd04b"
            background-color="#545c64"
            class="el-menu-vertical-demo"
            default-active="/"
            text-color="#fff"
            unique-opened="unique-opened"
            router
            style="height: 100vh;width: 15vw"
        >
          <el-menu-item index="/">
            <template #title>
              <span>用户主页</span>
            </template>
          </el-menu-item>
          <el-sub-menu index="userInformation">
            <template #title>
              <span>个人信息</span>
            </template>
            <el-menu-item index="/searchUserInformation">
              <span>查询个人信息</span>
            </el-menu-item>
            <el-menu-item index="/changeUserInformation">
              <span>修改个人信息</span>
            </el-menu-item>
          </el-sub-menu>
          <el-sub-menu index="studentInformation">
            <template #title>
              <span>学生信息</span>
            </template>
            <el-sub-menu>
              <template #title>
                <span>查询学生信息</span>
              </template>
              <el-menu-item index="/searchStudentInformationByObject">按项目</el-menu-item>
              <el-menu-item index="/searchStudentInformationByStudent">按学生</el-menu-item>
            </el-sub-menu>
            <el-menu-item :disabled="isStudent||isTeacher" index="/changeStudentInformation">
              <span>修改学生信息</span>
            </el-menu-item>
          </el-sub-menu>
          <el-sub-menu index="record">
            <template #title>
              <span>日志信息</span>
            </template>
            <el-menu-item index="/searchRecordByLaboratory">
              <span>按科研室查询</span>
            </el-menu-item>
            <el-menu-item index="/searchRecordByStudent">
              <span>按学生查询</span>
            </el-menu-item>
          </el-sub-menu>
          <el-sub-menu :disabled="isStudent" index="application">
            <template #title>
              <span>提交或处理申请</span>
            </template>
            <el-menu-item :disabled="isStudent||isAdmin" index="/submitEquipmentApplication">
              <span>提交设备申请</span>
            </el-menu-item>
            <el-menu-item :disabled="isStudent||isTeacher" index="/handleEquipmentApplication">
              <span>处理设备申请</span>
            </el-menu-item>
            <el-menu-item :disabled="isStudent||isAdmin" index="/submitProjectApplication">
              <span>提交项目申请</span>
            </el-menu-item>
            <el-menu-item :disabled="isStudent||isTeacher" index="/handleProjectApplication">
              <span>处理项目申请</span>
            </el-menu-item>
          </el-sub-menu>
          <el-sub-menu index="achievements">
            <template #title>
              <span>论文与专利信息</span>
            </template>
            <el-menu-item index="/searchAchievements">
              <span>查询论文与专利信息</span>
            </el-menu-item>
            <el-menu-item :disabled="isStudent" index="/changeAchievements">
              <span>修改论文与专利信息</span>
            </el-menu-item>
          </el-sub-menu>
          <el-sub-menu index="project">
            <template #title>
              <span>科研项目信息</span>
            </template>
            <el-menu-item index="/searchProject">
              <span>查询科研项目</span>
            </el-menu-item>
            <el-menu-item :disabled="isStudent" index="/changeProject">
              <span>修改科研项目</span>
            </el-menu-item>
          </el-sub-menu>
          <el-sub-menu index="teacher">
            <template #title>
              <span>指导老师信息</span>
            </template>
            <el-sub-menu index="searchTeacherInformation">
              <template #title>
                <span>查询指导老师信息</span>
              </template>
              <el-menu-item index="/searchTeacherByProject">按项目</el-menu-item>
              <el-menu-item index="/searchTeacherByTeacher">按老师</el-menu-item>
            </el-sub-menu>
            <el-menu-item :disabled="isStudent||isTeacher" index="/changeTeacherInformation">
              <span>修改指导老师信息</span>
            </el-menu-item>
          </el-sub-menu>
          <el-sub-menu index="equipment">
            <template #title>
              <span>设备信息</span>
            </template>
            <el-menu-item index="/searchEquipment">
              <span>查询设备信息</span>
            </el-menu-item>
            <el-menu-item :disabled="isStudent||isTeacher" index="/changeEquipment">
              <span>修改设备信息</span>
            </el-menu-item>
          </el-sub-menu>
          <el-sub-menu index="laboratory">
            <template #title>
              <span>科研室信息</span>
            </template>
            <el-menu-item index="/searchLaboratory">
              <span>查询科研室信息</span>
            </el-menu-item>
            <el-menu-item :disabled="isStudent||isTeacher" index="/changeLaboratory">
              <span>修改科研室信息</span>
            </el-menu-item>
          </el-sub-menu>
        </el-menu>
      </div>
      <div style="float:left;width: 85vw;height: 90vh">
        <router-view/>
      </div>
    </div>
  </div>


</template>

<script setup>
import router from "@/router"
import {ElMessage} from "element-plus";
import {useStore} from "@/stores";
import {get} from "@/net";

const store = useStore();

const isStudent = store.auth.user.identity === 'student';
const isTeacher = store.auth.user.identity === 'teacher';
const isAdmin = store.auth.user.identity === 'admin';


const logout = () => {
  get("/api/auth/logout", () => {
    ElMessage.success('退出登录成功');
    store.auth.user = 'null';
    router.push('/');
  })
}

</script>

<style scoped>
</style>