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
        <el-form-item>
          <el-select v-model="form.identity" :disabled="isIdValid" placeholder="请选择注册身份" style="width: 100%">
            <el-option label="管理员" value="admin"/>
            <el-option label="教师" value="teacher"/>
            <el-option label="学生" value="student"/>
            <template #prefix>
              <el-icon>
                <Star/>
              </el-icon>
            </template>
          </el-select>
        </el-form-item>
        <el-form-item prop="id">
          <div style="width: 100%">
            <el-row :gutter="15">
              <!--设置两个框的间隔-->
              <el-col :span="18">
                <el-input v-model="form.id" :disabled="isIdValid" :maxlength="6" placeholder="请输入身份码" type="text">
                  <template #prefix>
                    <el-icon>
                      <Link/>
                    </el-icon>
                  </template>
                </el-input>
              </el-col>
              <el-col :span="6">
                <el-button :disabled="isIdValid" type="success" @click="validateIdentity">
                  {{ isIdValid ? "重置请刷新" : "身份码验证" }}
                </el-button>
              </el-col>
            </el-row>
          </div>
        </el-form-item>
        <el-form-item prop="username">
          <el-input v-model="form.username" :disabled="!isIdValid" :maxlength="16" placeholder="请输入用户名" type="text">
            <template #prefix>
              <el-icon>
                <User/>
              </el-icon>
            </template>
          </el-input>
        </el-form-item>
        <el-form-item prop="password">
          <el-input v-model="form.password" :disabled="!isIdValid" :maxlength="16" placeholder="请输入密码" type="password">
            <template #prefix>
              <el-icon>
                <Lock/>
              </el-icon>
            </template>
          </el-input>
        </el-form-item>
        <el-form-item prop="password_repeat">
          <el-input v-model="form.password_repeat" :disabled="!isIdValid" placeholder="请重复密码" type="password">
            <template #prefix>
              <el-icon>
                <Lock/>
              </el-icon>
            </template>
          </el-input>
        </el-form-item>
        <el-form-item prop="email">
          <el-input v-model="form.email" :disabled="!isIdValid" placeholder="请输入电子邮件地址" type="email">
            <template #prefix>
              <el-icon>
                <Message/>
              </el-icon>
            </template>
          </el-input>
        </el-form-item>
        <el-form-item prop="code">
          <div style="width: 100%">
            <el-row :gutter="15">
              <!--设置两个框的间隔-->
              <el-col :span="18">
                <el-input v-model="form.code" :disabled="!isIdValid" :maxlength="6" placeholder="请输入验证码" type="text">
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
const isEmailValid = ref(false)
const isIdValid = ref(false)
const formRef = ref()
const coldTime = ref(0)

import router from "@/router"
import {reactive, ref} from "vue";
import {ElMessage} from "element-plus";
import {post} from "@/net";
import {Edit, Lock, Message, Star, User} from '@element-plus/icons-vue';

//准备表格
const form = reactive({
  id: '',
  identity: '',
  username: '',
  password: '',
  password_repeat: '',
  email: '',
  code: '',
})

//重复密码校验
const validatePassword_repeat = (rule, value, callback) => {
  if (value === '') {
    callback(new Error('请重新输入密码'))
  } else if (value !== form.password) {
    callback(new Error('两次输入的密码不一致'))
  } else {
    callback()
  }
}

//Id输入校验
const validateId = (rule, value, callback) => {
  if (value === '') {
    callback(new Error('请重新身份码'))
  } else if (!/^[^\u4E00-\u9FA5\x21-\x2F\x3A-\x40\x5B-\x60\x7B-\x7E]*$/.test(value)) {
    callback(new Error('身份码不能包含中文/特殊字符'))
  } else {
    callback()
  }
}

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
//定义表单填写规则
const rules = {
  identity: [
    {required: true, message: '请选择身份', trigger: ['blur', 'change']}
  ],
  id: [
    {validator: validateId, trigger: ['blur', 'change']}
  ],
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

//校验用户身份码
const validateIdentity = () => {
  post('api/auth/register-validateIdentity', {
    id: form.id,
    identity: form.identity,
  }, (message) => {
    ElMessage.success(message);
    isIdValid.value = true;
  }, (message) => {
    ElMessage.warning(message);
    isIdValid.value = false;
  })
}


//每次验证邮箱更新情况
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
        id: form.id,
        identity: form.identity,
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

//未输入合法邮箱之前 不能获取验证码
//获取验证码
const validateEmail = () => {
  post('api/auth/valid-register-email', {
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