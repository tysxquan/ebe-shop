package com.sxquan.core.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.http.HttpStatus;

import java.io.Serializable;

/**
 * @Description 统一接口返回
 * @Author sxquan
 * @Date 2019/12/20 15:20
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ServerResponse implements Serializable {

    /**
     * 响应代码
     */
    private int code;

    /**
     * 响应消息
     */
    private String message;

    /**
     * 响应结果
     */
    private Object data;

    private ServerResponse(int code) {
        this.code = code;
    }

    private ServerResponse(int code, Object data) {
        this.code = code;
        this.data = data;
    }

    private ServerResponse(int code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    private ServerResponse(int code, String message) {
        this.code = code;
        this.message = message;
    }

    //使之不在json序列化结果当中
    @JsonIgnore
    public boolean isSuccess() {
        return this.code == HttpStatus.OK.value();
    }

    public int getCode() {
        return code;
    }

    public Object getData() {
        return data;
    }

    public String getMessage() {
        return message;
    }


    public static ServerResponse success() {
        return new ServerResponse(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase());
    }

    public static ServerResponse successMessage(String message) {
        return new ServerResponse(HttpStatus.OK.value(), message);
    }

    public static ServerResponse success(Object data) {
        return new ServerResponse(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(),data);
    }

    public static ServerResponse success(String message, Object data) {
        return new ServerResponse(HttpStatus.OK.value(), message, data);
    }


    public static ServerResponse error() {
        return new ServerResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
    }


    public static ServerResponse error(String errorMessage) {
        return new ServerResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), errorMessage);
    }

    public static ServerResponse error(int errorCode, String errorMessage) {
        return new ServerResponse(errorCode, errorMessage);
    }
}