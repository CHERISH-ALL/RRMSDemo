<!--登录完成的页面设计-->
<template>
  <div style="width :100vw; height: 100vh; overflow:hidden">
    <div style="width: 100vw;height: 10vh; overflow:hidden;background-color: #545c64">
      <div style="float:left;width: 25vw;height: 10vh">
        <el-image fit="cover" src="./src/assets/login/gxunLogo.png"
                  style="max-width: 100%;height:100%;object-fit: cover"/>
      </div>
      <div style="float: left;width: 50vw;height: 10vh;"></div>


      <div style="float:left;width: 25vw;height: 10vh;">
        <div style="margin-left: 0.5vw;margin-top:1vh;;width: 24vw;height: 8vh">
          <div style="float:left;height: 8vh;width: 8vh;align-items: center;justify-content:center;display: flex;">
            <el-avatar :src="circleUrl" size="large"/>
          </div>
          <div style="float:left;margin-left: 1vw;width:19vw;height:8vh;text-align: center">
            <div
                style="float:left;margin-top:2vh;align-items: center;justify-content:center;display: flex;font-size: 3vh">
              欢迎xxx进入RRMS
            </div>
            <div
                style="margin-left:1vw;float:left;margin-top:2vh;align-items: center;justify-content:center;display: flex">
              <el-button type="danger" @click="logout()">退出登录</el-button>
            </div>
          </div>


        </div>
      </div>
    </div>
    <div style="width: 100vw;height: 100vh">
      <router-view v-slot="{ Component }">
        <transition mode="out-in" name="el-fade-in-linear">
          <component :is="Component"/>
        </transition>
      </router-view>
    </div>
  </div>


</template>

<script setup>
import router from "@/router"
import {ElMessage} from "element-plus";
// import {useStore} from "@/stores"

// const store = useStore();
const logout = () => {
  ElMessage.success("退出登录成功");
  // store.auth.user = null;
  router.push('/');
}
</script>

<style scoped>

</style>