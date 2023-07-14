package com.rrmsbackend.eneity;

import lombok.Data;

@Data
public class RestBean<T> {
    private int status;
    private boolean success;
    private T message;

    private RestBean(int status, boolean success, T message) {
        this.status = status;
        this.success = success;
        this.message = message;
    }

    private RestBean() {
    }

    ;// 工具对象私有化

    //包装工具方法
    public static <T> RestBean<T> success() {
        return new RestBean<T>(200, true, null);//登录成功
    }

    public static <T> RestBean<T> success(T data) {
        return new RestBean<T>(200, true, data);//登录成功
    }

    public static <T> RestBean<T> failure(int status, T data) {
        return new RestBean<>(status, false, data);
    }
}//封装传递的信息
