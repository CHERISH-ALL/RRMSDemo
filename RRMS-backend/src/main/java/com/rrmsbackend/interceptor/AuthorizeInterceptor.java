//拦截器
package com.rrmsbackend.interceptor;

import com.rrmsbackend.eneity.user.AccountUser;
import com.rrmsbackend.mapper.UserMapper;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;


@Component
public class AuthorizeInterceptor implements HandlerInterceptor {
    @Resource
    UserMapper userMapper;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        String name = authentication.getName();
        AccountUser account = userMapper.findAccountUserByNameOrEmail(name);
        request.getSession().setAttribute("account", account);//将结果存入session中
        return true;
    }
}
