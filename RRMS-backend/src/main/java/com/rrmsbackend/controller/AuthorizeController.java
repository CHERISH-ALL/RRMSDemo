package com.rrmsbackend.controller;

import com.rrmsbackend.eneity.RestBean;
import com.rrmsbackend.service.AuthorizeService;
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
public class AuthorizeController {

    //用正则表达式确定填入数据条件
    private final String USERNAME_REGEXP = "^[a-zA-Z0-9\u4E00-\u9FA5]+$";
    private final String EMAIL_REGEXP = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";


    @Resource
    AuthorizeService authorizeService;

    @PostMapping("/valid-register-email")//校验注册邮箱
    public RestBean<String> validateRegisterEmail(@Pattern(regexp = EMAIL_REGEXP)
                                                  @RequestParam("email") String email, HttpSession httpSession) {
        String s = authorizeService.sendValidateEmail(email, httpSession.getId(), false);
        if (s == null) {
            return RestBean.success("邮件已发送,请注意查收");
        } else {
            return RestBean.failure(400, s);
        }
    }

    @PostMapping("/valid-reset-email")//校验重置邮箱
    public RestBean<String> validateResetEmail(@Pattern(regexp = EMAIL_REGEXP)
                                               @RequestParam("email") String email, HttpSession httpSession) {
        String s = authorizeService.sendValidateEmail(email, httpSession.getId(), true);
        if (s == null) {
            return RestBean.success("邮件已发送,请注意查收");
        } else {
            return RestBean.failure(400, s);
        }
    }

    @PostMapping("/register")//注册功能
    public RestBean<String> registerUser(@RequestParam("id") long id,
                                         @RequestParam("identity") String identity,
                                         @Pattern(regexp = USERNAME_REGEXP)
                                         @Length(min = 2, max = 16) @RequestParam("username") String username,
                                         @Length(min = 6, max = 16) @RequestParam("password") String password,
                                         @Pattern(regexp = EMAIL_REGEXP) @RequestParam("email") String email,
                                         @Length(min = 6, max = 6) @RequestParam("code") String code,
                                         HttpSession httpSession) {
        String s = authorizeService.validateAndRegister(id, identity, username, password, email, code, httpSession.getId());
        if (s == null) {
            return RestBean.success("注册成功,请完成登录");
        } else {
            return RestBean.failure(400, s);
        }
    }

    @PostMapping("/start-reset")//开始重置
    public RestBean<String> startReset(@Pattern(regexp = EMAIL_REGEXP) @RequestParam("email") String email,
                                       @Length(min = 6, max = 6) @RequestParam("code") String code,
                                       HttpSession session) {
        String s = authorizeService.validateOnly(email, code, session.getId());
        if (s == null) {
            session.setAttribute("resetPassword", email);
            return RestBean.success();
        } else {
            return RestBean.failure(400, s);
        }
    }

    @PostMapping("/do-reset")//完成重置
    public RestBean<String> resetPassword(@Length(min = 6, max = 16) @RequestParam("password") String password,
                                          HttpSession session) {
        String email = (String) session.getAttribute("resetPassword");
        if (email == null) {
            return RestBean.failure(400, "请先完成邮箱验证");
        } else if (authorizeService.resetPassword(password, email)) {
            session.removeAttribute("password");
            return RestBean.success("密码重置成功");
        } else {
            return RestBean.failure(400, "内部错误请联系管理员");
        }
    }

    @PostMapping("/register-validateIdentity")
    public RestBean<String> validateId(@RequestParam("id") long id,
                                       @RequestParam("identity") String identity) {
        String s = authorizeService.validateIdentity(identity, id);
        if (s == null) {
            return RestBean.success("您可以以此身份注册");
        } else {
            return RestBean.failure(400, s);
        }
    }
}
