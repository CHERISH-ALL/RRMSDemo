package com.rrmsbackend.service;

import com.rrmsbackend.eneity.Admin;
import com.rrmsbackend.mapper.AdminMapper;
import jakarta.annotation.Resource;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AdminService implements UserDetailsService {

    @Resource
    AdminMapper adminMapper;

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
}//查找admin
