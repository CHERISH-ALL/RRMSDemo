<!--先验证再重置密码-->
<template>
  <div style="margin: 0 30px;text-align: center">
    <div style="margin-top: 150px">
      <div style="text-align: left">
        <el-image fit="cover" src="./src/assets/login/gxunLogo.png"
                  style="width: 100%;height: 100%"/>
        <el-steps :active="active" align-center finish-status="success">
          <el-step title="验证邮箱"/>
          <el-step title="重新设定密码"/>
        </el-steps>
      </div>
    </div>
    <div style="margin-top: 50px">
      <transition mode="out-in" name="el-fade-in-linear">
        <div v-if="active === 0">
          <el-form ref="formRef" :model="form" :rules="rules" @validate="onValidate">
            <el-form-item prop="email">
              <el-input v-model="form.email" placeholder="请输入需要重置的用户邮箱" type="email">
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
          <div style="margin-top: 40px">
            <el-button plain style="width: 270px;" type="danger" @click="startRest()">开始重置密码</el-button>
          </div>
        </div>
      </transition>
      <transition mode="out-in" name="el-fade-in-linear">
        <div v-if="active===1">
          <el-form ref="formRef" :model="form" :rules="rules" @validate="onValidate">
            <el-form-item prop="password">
              <el-input v-model="form.password" :maxlength="16" placeholder="请输入新密码" type="password">
                <template #prefix>
                  <el-icon>
                    <Lock/>
                  </el-icon>
                </template>
              </el-input>
            </el-form-item>
            <el-form-item prop="password_repeat">
              <el-input v-model="form.password_repeat" placeholder="请重复新密码" type="password">
                <template #prefix>
                  <el-icon>
                    <Lock/>
                  </el-icon>
                </template>
              </el-input>
            </el-form-item>
          </el-form>
          <div style="margin-top: 40px">
            <el-button plain style="width: 270px;" type="danger" @click="doReset()">立即重置密码</el-button>
          </div>
        </div>
      </transition>
    </div>
  </div>
</template>

<script setup>
import {Edit, Lock, Message} from '@element-plus/icons-vue';
import {reactive, ref} from "vue";
import {post} from "@/net";
import {ElMessage} from "element-plus";
import router from "@/router";

const active = ref(0);

const form = reactive({
  email: '',
  code: '',
  password: '',
  password_repeat: '',
})

const isEmailValid = ref(false)
const formRef = ref()
const coldTime = ref(0)

//每次验证邮箱更新情况
const onValidate = (prop, isValid) => {
  if (prop === 'email') {
    isEmailValid.value = isValid;
  }
}

//获取验证码
const validateEmail = () => {
  post('api/auth/valid-reset-email', {
    email: form.email
  }, (message) => {
    ElMessage.success(message);
    coldTime.value = 60
    setInterval(() => coldTime.value--, 1000)//设定定时器
  })
}

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

//定义表单填写规则
const rules = {
  email: [
    {required: true, message: '请输入邮件地址', trigger: ['blur', 'change']},
    {type: 'email', message: '请输入合法的邮箱地址', trigger: ['blur', 'change']},
  ],
  code: [
    {required: true, message: '请输入获取的验证码', trigger: 'blur'},
  ],
  password: [
    {required: true, message: '请输入密码', trigger: ['blur', 'change']},
    {min: 6, max: 16, message: '密码长度必须在6到16个字符之间', trigger: ['blur', 'change']}
  ],
  password_repeat: [
    {validator: validatePassword_repeat, trigger: ['blur', 'change']}
  ],
}

const startRest = () => {
  formRef.value.validate((isValid) => {
    if (isValid) {
      post('api/auth/start-reset', {
        email: form.email,
        code: form.code,
      }, () => {
        active.value++;
      })
    } else {
      ElMessage.warning('请填写邮件地址和验证码')
    }
  })
}

const doReset = () => {
  formRef.value.validate((isValid) => {
    if (isValid) {
      post('api/auth/do-reset', {
        password: form.password
      }, () => {
        ElMessage.success('密码重置成功')
        router.push('/')
      })
    } else {
      ElMessage.warning('请填写新密码')
    }
  })
}


</script>


<style scoped>

</style>