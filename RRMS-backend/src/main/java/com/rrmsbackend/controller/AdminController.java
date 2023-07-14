package com.rrmsbackend.controller;

import com.rrmsbackend.eneity.RestBean;
import com.rrmsbackend.service.AdminService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.constraints.Pattern;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@Validated
@RestController
@RequestMapping("/api/auth")
public class AdminController {

    private final String EMAIL_REGEXP = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";

    @Resource
    AdminService adminService;

    @PostMapping("/valid-email")//验证邮件
    public RestBean<String> validateEmail(@Pattern(regexp = EMAIL_REGEXP)
                                          @RequestParam("email") String email, HttpSession httpSession) {
        if (adminService.sendValidateEmail(email, httpSession.getId())) {
            return RestBean.success("邮件已发送,请注意查收");
        } else {
            return RestBean.failure(400, "邮件发送失败，请联系管理员");
        }
    }

}
