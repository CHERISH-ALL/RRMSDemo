<!--登录界面左侧（相同）内容的显示-->
<template>
  <div style="width :100vw; height: 100vh; overflow:hidden;display: flex">
    <div style="flex: 1">
      <!--      <el-image  style="width: 100%;height: 100%"-->
      <!--                 src="https://cn.bing.com/images/search?view=detailV2&ccid=Md86Wi2E&id=94E5B2B0F268680EDC09B59CFF9383E54D3A41EC&thid=OIP.Md86Wi2EYiKHNPldRZiD4gHaEo&mediaurl=https%3a%2f%2fts1.cn.mm.bing.net%2fth%2fid%2fR-C.31df3a5a2d8462228734f95d459883e2%3frik%3d7EE6TeWDk%252f%252bctQ%26riu%3dhttp%253a%252f%252fwww.quazero.com%252fuploads%252fallimg%252f140303%252f1-140303214331.jpg%26ehk%3dSpI7mz%252byLqOkT8BL79jcd3iCtQYNFlBHQzbtF1p0vuQ%253d%26risl%3d%26pid%3dImgRaw%26r%3d0&exph=1050&expw=1680&q=%e5%9b%be%e7%89%87&simid=608003267543525608&FORM=IRPRST&ck=05FC30022211F46A8B3E3FE8E8D3E082&selectedIndex=1&ajaxhist=0&ajaxserp=0"/>-->
      <el-image fit="cover" src="./src/assets/login/loginBackground.jpg"
                style="width: 100%;height: 100%"/>
    </div>
    <div class="welcomeTitle">
      <div style="text-shadow: 0 0 10px black;font-size:30px;">欢迎来到科研室登陆系统</div>
      <div style="margin-top: 10px">在这里你可以参与科研课题</div>
    </div>
    <div style="width: 500px;background-color:white;z-index:1">
      <!--引入动态效果-->
      <!--之后的内容在路由中寻找-->
      <router-view v-slot="{ Component }">
        <transition mode="out-in" name="el-fade-in-linear">
          <component :is="Component"/>
        </transition>
      </router-view>
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