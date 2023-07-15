<!--登录完成的页面设计-->
<template>
  <div>
    欢迎来的rrms;
  </div>
  <div>
    <el-button type="danger" @click="logout()">退出登录</el-button>
  </div>
</template>

<script setup>
import router from "@/router"
import {ElMessage} from "element-plus";


const logout = () => {
  ElMessage.success("退出登录成功");
  router.push('/');
}
</script>

<style scoped>

</style>