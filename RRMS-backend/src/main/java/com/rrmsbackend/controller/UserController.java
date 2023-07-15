package com.rrmsbackend.controller;

import com.rrmsbackend.eneity.RestBean;
import com.rrmsbackend.service.UserService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Length;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@Validated
@RestController
@RequestMapping("/api/auth")
public class UserController {

    //用正则表达式确定填入数据条件
    private final String USERNAME_REGEXP = "^[a-zA-Z0-9\u4E00-\u9FA5]+$";
    private final String EMAIL_REGEXP = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";


    @Resource
    UserService userService;

    @PostMapping("/valid-email")//验证邮件
    public RestBean<String> validateEmail(@Pattern(regexp = EMAIL_REGEXP)
                                          @RequestParam("email") String email, HttpSession httpSession) {
        String s = userService.sendValidateEmail(email, httpSession.getId());
        if (s == null) {
            return RestBean.success("邮件已发送,请注意查收");
        } else {
            return RestBean.failure(400, s);
        }
    }

    @PostMapping("/register")//注册功能
    public RestBean<String> registerUser(@Pattern(regexp = USERNAME_REGEXP) @Length(min = 2, max = 16) @RequestParam("username") String username,
                                         @Length(min = 6, max = 16) @RequestParam("password") String password,
                                         @Pattern(regexp = EMAIL_REGEXP) @RequestParam("email") String email,
                                         @Length(min = 6, max = 6) @RequestParam("code") String code,
                                         HttpSession httpSession) {
        String s = userService.validateAndRegister(username, password, email, code, httpSession.getId());
        if (s == null) {
            return RestBean.success("注册成功,请完成登录");
        } else {
            return RestBean.failure(400, s);
        }
    }
}
