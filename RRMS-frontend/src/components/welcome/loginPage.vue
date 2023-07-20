<!--实现登录验证功能-->
<template>
  <div style="margin: 0 30px;text-align: center">
    <div style="margin-top: 150px">
      <div style="text-align: left">
        <el-image fit="cover" src="./src/assets/login/gxunLogo.png"
                  style="width: 100%;height: 100%"/>
      </div>
      <div style="font-size: 14px;color: gray">进入系统前请先输入用户名和密码登录</div>
    </div>
    <div style="margin-top: 50px">
      <el-input v-model="form.username" placeholder="用户名/邮箱登录" style="margin: 10px" type="text">
        <template #prefix>
          <el-icon>
            <User/>
          </el-icon>
        </template>
      </el-input>
      <el-input v-model="form.password" placeholder="密码" style="margin:10px" type="password">
        <template #prefix>
          <el-icon>
            <Lock/>
          </el-icon>
        </template>
      </el-input>
      <div style="margin: 10px">
        <el-row>
          <el-col :span="12" style="text-align: left">
            <el-checkbox v-model="form.remember" label="记住我"/>
          </el-col>
          <el-col :span="12" style="text-align: right">
            <el-link @click="router.push('/forget')">忘记密码？</el-link>
          </el-col>
        </el-row>
      </div>
    </div>
    <div style="margin: 40px">
      <el-button plain style="width: 270px" type="success" @click="login()">立即登录</el-button>
    </div>
    <el-divider>
      <span style="color:grey;font-size: 15px">没有账号?</span>
    </el-divider>
    <div style="margin: 40px">
      <el-button plain style="width: 270px" type="warning" @click="router.push('/register')">注册账号</el-button>
    </div>
  </div>
</template>

<script setup>
import {Lock, User} from '@element-plus/icons-vue';
import {ElMessage} from "element-plus";
import {reactive} from "vue";
import {get, post} from "@/net";
import router from "@/router"
// import {useStore} from '@/stores'

const form = reactive({
  username: '',
  password: '',
  remember: false
})//表单默认传递的信息


// const store = useStore();
const login = () => {
  if (!form.username || !form.password) {
    ElMessage.warning('请输入用户名和密码！')
  } else {
    post('/api/auth/login', {
      username: form.username,
      password: form.password,
      remember: form.remember
    }, (message) => {
      ElMessage.success(message);
      router.push('/index');
    })//获取表单信息并验证转页
  }
}
</script>
<style scoped>

</style>