package com.rrmsbackend.service;

import org.springframework.security.core.userdetails.UserDetailsService;

public interface AuthorizeService extends UserDetailsService {
    //发送邮件
    String sendValidateEmail(String email, String sessionId, boolean hasUser);//发送邮件功能

    String validateAndRegister(long id, String identity, String username, String userRealName, String password, String email, String code, String sessionId);//注册并登录功能

    String validateOnly(String email, String code, String sessionId);

    String validateIdentity(String identity, long id);

    Boolean resetPassword(String password, String email);
}
