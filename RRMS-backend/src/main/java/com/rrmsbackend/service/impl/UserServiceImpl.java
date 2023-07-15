package com.rrmsbackend.service.impl;

import com.rrmsbackend.eneity.User;
import com.rrmsbackend.mapper.UserMapper;
import com.rrmsbackend.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;
import java.util.concurrent.TimeUnit;


@Service
public class UserServiceImpl implements UserService {

    @Resource
    UserMapper userMapper;
    /*
        1、先生成对应的验证码
        2、发送验证码到指定邮箱
        3、把邮箱和验证码发到redis中（过期时间3min，如果此时要求重新发邮件，如果时间低于2min，可重新发送，重复此流程）
        4、如果发送失败redis刚刚插入的删除
        5、用户在注册时再从redis中取出键值对，进行验证
        */
    @Resource
    MailSender mailSender;
    @Value("${spring.mail.username}")
    String from;
    @Resource
    private StringRedisTemplate stringRedisTemplate;
    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (username == null) {
            throw new UsernameNotFoundException("用户名不能为空");
        }
        User user = userMapper.findAdminByUsernameOrEmail(username);
        if (user == null) {
            throw new UsernameNotFoundException("用户名或密码错误");
        }
        return org.springframework.security.core.userdetails.User
                .withUsername(user.getUsername())
                .password(user.getPassword())//不加密用{noop}
                .roles("user")
                .build();
    }

    @Override
    public String sendValidateEmail(String email, String sessionId) {
        String key = "email:" + sessionId + ":" + email;
        if (Boolean.TRUE.equals(stringRedisTemplate.hasKey(key))) {
            long expire = Optional.ofNullable(stringRedisTemplate.getExpire(key, TimeUnit.SECONDS)).orElse(0L);
            if (expire > 120) return "请求频繁，请稍后再试";
        }
        if (userMapper.findAdminByUsernameOrEmail(email) != null) {//账号已经被注册过了
            return "此邮箱已被其他用户注册";
        }
        Random random = new Random();
        int code = random.nextInt(899999) + 100000;//先生成对应的验证码
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);//发送地址
        message.setTo(email);//收件地址
        message.setSubject("您的验证邮件");//标题
        message.setText("验证码是:" + code);//正文
        try {
            mailSender.send(message);
            stringRedisTemplate.opsForValue().set(key, String.valueOf(code), 3, TimeUnit.MINUTES);//把邮箱和验证码发到redis中
            return null;
        } catch (MailException e) {
            e.printStackTrace();
            return "邮件发送失败，请联系管理员";
        }//发送验证码到指定邮箱
    }

    @Override
    public String validateAndRegister(String username, String password, String email, String code, String sessionId) {
        String key = "email:" + sessionId + ":" + email;
        if (Boolean.TRUE.equals(stringRedisTemplate.hasKey(key))) {//做过验证
            String s = stringRedisTemplate.opsForValue().get(key);
            if (s == null) {//验证超时
                return "验证码失效，请重新获取";
            }
            if (s.equals(code)) {//对比验证码
                password = encoder.encode(password);
                if (userMapper.createUser(username, password, email) > 0) {//插入成功
                    return null;//
                } else {
                    return "内部错误，请联系管理员";
                }
            } else {
                return "验证码错误，请检查后再提交";
            }
        } else {
            return "请先获取验证码";
        }
    }
}//验证和注册
