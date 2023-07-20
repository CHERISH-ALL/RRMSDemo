<template>
  <div style="overflow:hidden">
    <el-menu
        active-text-color="#ffd04b"
        background-color="#545c64"
        class="el-menu-vertical-demo"
        default-active="1-1"
        style="height: 100vh;width: 15vw;"
        text-color="#fff"
        unique-opened="unique-opened"
    >
      <el-sub-menu index="1">
        <template #title>
          <span>个人信息</span>
        </template>
        <el-menu-item index="1-1">
          <span>查询个人信息</span>
        </el-menu-item>
        <el-menu-item index="1-2">
          <span>修改个人信息</span>
        </el-menu-item>
      </el-sub-menu>
      <el-sub-menu index="2">
        <template #title>
          <span>学生信息</span>
        </template>
        <el-sub-menu index="2-1">
          <template #title>
            <span>查询学生信息</span>
          </template>
          <el-menu-item index="2-1-1">按项目</el-menu-item>
          <el-menu-item index="2-1-2">按学生</el-menu-item>
        </el-sub-menu>
        <el-menu-item index="2-2">
          <span>修改学生信息</span>
        </el-menu-item>
      </el-sub-menu>
      <el-sub-menu index="3">
        <template #title>
          <span>日志信息</span>
        </template>
        <el-menu-item index="3-1">
          <span>按科研室查询</span>
        </el-menu-item>
        <el-menu-item index="3-2">
          <span>按学生查询</span>
        </el-menu-item>
      </el-sub-menu>
      <el-sub-menu index="4">
        <template #title>
          <span>提交或处理申请</span>
        </template>
        <el-menu-item index="4-1">
          <span>处理设备申请</span>
        </el-menu-item>
        <el-menu-item index="4-2">
          <span>设备申请</span>
        </el-menu-item>
        <el-menu-item index="4-3">
          <span>提交项目申请</span>
        </el-menu-item>
        <el-menu-item index="4-4">
          <span>安排科研室</span>
        </el-menu-item>
      </el-sub-menu>
      <el-sub-menu index="5">
        <template #title>
          <span>论文与专利信息</span>
        </template>
        <el-menu-item index="5-1">
          <span>查询论文与专利信息</span>
        </el-menu-item>
        <el-menu-item index="5-2">
          <span>添加论文与专利信息</span>
        </el-menu-item>
        <el-menu-item index="5-3">
          <span>修改论文与专利信息</span>
        </el-menu-item>
      </el-sub-menu>
      <el-sub-menu index="6">
        <template #title>
          <span>科研项目信息</span>
        </template>
        <el-menu-item index="6-1">
          <span>查询科研项目</span>
        </el-menu-item>
        <el-menu-item index="6-2">
          <span>修改科研项目</span>
        </el-menu-item>
      </el-sub-menu>
      <el-sub-menu index="7">
        <template #title>
          <span>指导老师信息</span>
        </template>
        <el-sub-menu index="7-1">
          <template #title>
            <span>查询指导老师信息</span>
          </template>
          <el-menu-item index="7-1-1">按项目</el-menu-item>
          <el-menu-item index="7-1-2">按老师</el-menu-item>
        </el-sub-menu>
        <el-menu-item index="7-2">
          <span>修改指导老师信息</span>
        </el-menu-item>
      </el-sub-menu>
      <el-sub-menu index="8">
        <template #title>
          <span>设备信息</span>
        </template>
        <el-menu-item index="8-1">
          <span>查询设备信息</span>
        </el-menu-item>
        <el-menu-item index="8-2">
          <span>修改设备信息</span>
        </el-menu-item>
      </el-sub-menu>
      <el-sub-menu index="9">
        <template #title>
          <span>科研室信息</span>
        </template>
        <el-menu-item index="9-1">
          <span>查询科研室信息</span>
        </el-menu-item>
        <el-menu-item index="9-2">
          <span>修改科研室信息</span>
        </el-menu-item>
      </el-sub-menu>
    </el-menu>
  </div>
</template>

<script setup>
import {Document, Menu as IconMenu, Location, Setting, User} from '@element-plus/icons-vue'
import {ref} from 'vue'

const activeIndex = ref(1)
//
// const handleSelect = (key: string, keyPath: string[]) => {
//   console.log(key, keyPath)
// }
//
// const handleOpen = (key: string, keyPath: string[]) => {
//   console.log(key, keyPath)
// }
// const handleClose = (key: string, keyPath: string[]) => {
//   console.log(key, keyPath)
// }
</script>

<style scoped>

</style>