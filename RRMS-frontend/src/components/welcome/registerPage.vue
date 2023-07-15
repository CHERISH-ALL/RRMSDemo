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
      <!--定义校验规则-->
      <!--@validate:绑定事件-->
      <el-form ref="formRef" :model="form" :rules="rules" @validate="onValidate">
        <!--        <el-form-item>-->
        <!--          输入基本信息-->
        <!--                  <el-input v-model="form." type="text" placeholder="学号" style="margin-top: 10px">-->
        <!--                    <template #prefix>-->
        <!--                      <el-icon>-->
        <!--                        <User/>-->
        <!--                      </el-icon>-->
        <!--                    </template>-->
        <!--                  </el-input>-->
        <!--        </el-form-item>-->
        <el-form-item prop="username">
          <el-input v-model="form.username" :maxlength="16" placeholder="用户名" type="text">
            <template #prefix>
              <el-icon>
                <User/>
              </el-icon>
            </template>
          </el-input>
        </el-form-item>
        <el-form-item prop="password">
          <el-input v-model="form.password" :maxlength="16" placeholder="密码" type="password">
            <template #prefix>
              <el-icon>
                <Lock/>
              </el-icon>
            </template>
          </el-input>
        </el-form-item>
        <el-form-item prop="password_repeat">
          <el-input v-model="form.password_repeat" placeholder="重复密码" type="password">
            <template #prefix>
              <el-icon>
                <Lock/>
              </el-icon>
            </template>
          </el-input>
        </el-form-item>
        <el-form-item prop="email">
          <el-input v-model="form.email" placeholder="电子邮件地址" type="email">
            <template #prefix>
              <el-icon>
                <Message/>
              </el-icon>
            </template>
          </el-input>
        </el-form-item>
        <el-form-item prop="code">
          <div>
            <el-row :gutter="15">
              <!--设置两个框的间隔-->
              <el-col :span="18">
                <el-input v-model="form.code" :maxlength="6" placeholder="请输入验证码" type="text">
                  <template #prefix>
                    <el-icon>
                      <Edit/>
                    </el-icon>
                  </template>
                </el-input>
              </el-col>
              <el-col :span="6">
                <el-button :disabled="!isEmailValid||coldTime > 0" type="success" @click="validateEmail">
                  {{ coldTime > 0 ? "请稍后" + coldTime + "秒" : "获取验证码" }}
                </el-button>
              </el-col>
            </el-row>
          </div>
        </el-form-item>
      </el-form>
    </div>
    <div style="margin-top: 40px">
      <el-button plain style="width: 270px" type="warning" @click="register">立即注册</el-button>
      <div style="margin-top: 15px">
        <span style="font-size: 14px;line-height: 14px;color: gray">已有账号？</span>
        <el-link style="translate:0 -2px" type="primary" @click="router.push('/')">立即登录</el-link>
      </div>
    </div>
  </div>
</template>

<script setup>
import router from "@/router"
import {reactive, ref} from "vue";
import {ElMessage} from "element-plus";
import {post} from "@/net";

const form = reactive({
  username: '',
  password: '',
  password_repeat: '',
  email: '',
  code: '',
})
//自定义用户名校验规则
const validateUsername = (rule, value, callback) => {
  if (value === '') {
    callback(new Error('请输入用户名'))
  } else if (!/^[a-zA-Z0-9\u4E00-\u9FA5]+$/.test(value)) {//判断是否符合正则表达式
    callback(new Error('用户名不能包含特殊字符，只能是中文/英文组合'))
  } else {
    callback()
  }
}

const validatePassword_repeat = (rule, value, callback) => {
  if (value === '') {
    callback(new Error('请重新输入密码'))
  } else if (value !== form.password) {
    callback(new Error('两次输入的密码不一致'))
  } else {
    callback()
  }
}

//定义表单填写规则
const rules = {
  username: [
    {validator: validateUsername, trigger: ['blur', 'change']},
    {min: 2, max: 16, message: '用户名长度必须在2到16个字符之间', trigger: ['blur', 'change']}
  ],
  password: [
    {required: true, message: '请输入密码', trigger: ['blur', 'change']},
    {min: 6, max: 16, message: '密码长度必须在6到16个字符之间', trigger: ['blur', 'change']}
  ],
  password_repeat: [
    {validator: validatePassword_repeat, trigger: ['blur', 'change']}
  ],
  email: [
    {required: true, message: '请输入邮件地址', trigger: ['blur', 'change']},
    {type: 'email', message: '请输入合法的邮箱地址', trigger: ['blur', 'change']},
  ],
  code: [
    {required: true, message: '请输入获取的验证码', trigger: 'blur'},
  ]
}

//未输入合法邮箱之前 不能获取验证码
const isEmailValid = ref(false)
const formRef = ref()
const coldTime = ref(0)

//没次验证邮箱更新情况
const onValidate = (prop, isValid) => {
  if (prop === 'email') {
    isEmailValid.value = isValid;
  }
}


//信息完全正确后允许注册
const register = () => {
  formRef.value.validate((isValid) => {
    if (isValid) {
      post('/api/auth/register', {//向后端发起请求
        username: form.username,
        password: form.password,
        email: form.email,
        code: form.code
      }, (message) => {
        ElMessage.success(message);
        router.push("/")
      })
    } else {
      ElMessage.warning('请完整填写上述表单内容')
    }
  })
}

//获取验证码
const validateEmail = () => {
  post('api/auth/valid-email', {
    email: form.email
  }, (message) => {
    ElMessage.success(message);
    coldTime.value = 60
    setInterval(() => coldTime.value--, 1000)//设定定时器
  })
}

</script>

<style scoped>

</style>