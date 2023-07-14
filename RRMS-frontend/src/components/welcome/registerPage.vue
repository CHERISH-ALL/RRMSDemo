<!--注册页面-->
<template>
  <div style="margin: 0 30px;text-align: center">
    <div style="margin-top: 100px">
      <div style="text-align: left">
        <el-image fit="cover" src="./src/assets/login/gxunLogo.png"
                  style="width: 100%;height: 100%"/>
      </div>
      <div style="font-size: 14px;color: gray">欢迎注册rrms，请在下方填写个人信息</div>
    </div>
    <div style="margin-top: 50px">
      <!--输入基本信息-->
      <!--        <el-input v-model="form." type="text" placeholder="学号" style="margin-top: 10px">-->
      <!--          <template #prefix>-->
      <!--            <el-icon>-->
      <!--              <User/>-->
      <!--            </el-icon>-->
      <!--          </template>-->
      <!--        </el-input>-->
      <el-input v-model="form.username" placeholder="用户名" style="margin-top: 10px" type="text">
        <template #prefix>
          <el-icon>
            <User/>
          </el-icon>
        </template>
      </el-input>
      <el-input v-model="form.password" placeholder="密码" style="margin-top: 10px" type="password">
        <template #prefix>
          <el-icon>
            <Lock/>
          </el-icon>
        </template>
      </el-input>
      <el-input v-model="form.password_repeat" placeholder="重复密码" style="margin-top: 10px" type="password">
        <template #prefix>
          <el-icon>
            <Lock/>
          </el-icon>
        </template>
      </el-input>
      <el-input v-model="form.email" placeholder="电子邮件地址" style="margin-top: 10px" type="email">
        <template #prefix>
          <el-icon>
            <Message/>
          </el-icon>
        </template>
      </el-input>
    </div>
    <div style="margin-top: 10px">
      <el-row :gutter="10">
        <!--设置两个框的间隔-->
        <el-col :span="18">
          <el-input v-model="form.code" placeholder="请输入验证码" type="text">
            <template #prefix>
              <el-icon>
                <Edit/>
              </el-icon>
            </template>
          </el-input>
        </el-col>
        <el-col span="6">
          <el-button type="success">获取验证码</el-button>
        </el-col>
      </el-row>
    </div>
    <div style="margin-top: 40px">
      <el-button plain style="width: 270px" type="warning">立即注册</el-button>
      <div style="margin-top: 15px">
        <span style="font-size: 14px;line-height: 14px;color: gray">已有账号？</span>
        <el-link style="translate:0 -2px" type="primary" @click="router.push('/')">立即登录</el-link>
      </div>
    </div>
  </div>
</template>

<script setup>
import router from "@/router"
import {reactive} from "vue";

const form = reactive({
  username: '',
  password: '',
  password_repeat: '',
  email: '',
  code: '',
})
</script>

<style scoped>

</style>