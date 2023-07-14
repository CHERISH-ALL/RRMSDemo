package com.rrmsbackend.service.impl;

import com.rrmsbackend.eneity.Admin;
import com.rrmsbackend.mapper.AdminMapper;
import com.rrmsbackend.service.AdminService;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Random;


@Service
public class AdminServiceImpl implements AdminService {

    @Resource
    AdminMapper adminMapper;
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

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (username == null) {
            throw new UsernameNotFoundException("用户名不能为空");
        }
        Admin admin = adminMapper.findAdminByAname(username);
        if (admin == null) {
            throw new UsernameNotFoundException("用户名或密码错误");
        }
        return User
                .withUsername(admin.getAname())
                .password("{noop}" + admin.getAmima())//不加密用{noop}
                .roles("user")
                .build();
    }

    @Override
    public boolean sendValidateEmail(String email, String sessionId) {
        String key = "email:" + sessionId + ":" + email;
//        if (Boolean.TRUE.equals(stringRedisTemplate.hasKey(key))) {
//            long expire = Optional.ofNullable(stringRedisTemplate.getExpire(key, TimeUnit.SECONDS)).orElse(0L);
//            if (expire > 120) return false;
//        }
        Random random = new Random();
        int code = random.nextInt(899999 + 100000);//先生成对应的验证码
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);//发送地址
        message.setTo(email);//收件地址
        message.setSubject("您的验证邮件");//标题
        message.setText("验证码是:" + code);//正文
        try {
            mailSender.send(message);
//            stringRedisTemplate.opsForValue().set(key, String.valueOf(code), 3, TimeUnit.MINUTES);//把邮箱和验证码发到redis中
            return true;
        } catch (MailException e) {
            e.printStackTrace();
            return false;
        }//发送验证码到指定邮箱
    }

}//查找admin
