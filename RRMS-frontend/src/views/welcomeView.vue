<!--登录界面左侧（相同）内容的显示-->
<template>
  <div style="width :100vw; height: 100vh; overflow:hidden;display: flex">
    <div style="flex: 1;background-color: blue">
      <el-image fit="cover" src="./src/assets/login/loginBackground.jpg"
                style="width: 100%;height: 100%"/>
    </div>
    <div class="welcomeTitle">
      <div style="text-shadow: 0 0 10px black;font-size:30px;">欢迎来到科研室登陆系统</div>
      <div style="margin-top: 10px">在这里你可以参与科研课题</div>
    </div>
    <div style="width: 500px;background-color:white">
      <!--引入动态效果-->
      <transition name="el-fade-in-linear">
        <!--之后的内容在路由中寻找-->
        <router-view/>
      </transition>

    </div>
  </div>
</template>

<script setup>

</script>

<style scoped>
.welcomeTitle {
  position: absolute;
  bottom: 30px;
  left: 30px;
  color: white;
}
</style>