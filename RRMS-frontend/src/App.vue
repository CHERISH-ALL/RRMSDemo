<!--最先访问文件-->
<script setup>
import {get} from "@/net";
import {useStore} from "@/stores";
import router from "@/router";

const store = useStore();

if (store.auth.user === 'null') {
  get("/api/user/me", (message) => {
    store.auth.user = message
    router.push('/index')
  }, () => {
    store.auth.user = 'null'
  })
}


</script>

<template>
  <!--  之后的内容在路由中寻找-->
  <router-view/>
</template>

<style scoped>

</style>
