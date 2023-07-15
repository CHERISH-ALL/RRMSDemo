package com.rrmsbackend.service;

import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    //发送邮件
    String sendValidateEmail(String email, String sessionId);//发送邮件功能

    String validateAndRegister(String username, String password, String email, String code, String sessionId);//注册并登录功能
}
