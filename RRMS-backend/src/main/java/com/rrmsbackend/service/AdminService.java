package com.rrmsbackend.service;

import org.springframework.security.core.userdetails.UserDetailsService;

public interface AdminService extends UserDetailsService {
    //发送邮件
    boolean sendValidateEmail(String email, String sessionId);
}
