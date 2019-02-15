package com.volarpadcloud.api.base;

import java.io.Serializable;

/**
 * @author linzibin
 * @email Zerozibin@163.com
 * @application
 */
public class BaseResponse<T> implements Serializable {

    private static final long serialVersionUID = -2155221128570591666L;

    private static final Integer CODE_SUCCESS = 200;
    private static final Integer CODE_FAIL = 500;

    private Integer code;
    private T data;
    private String msg;

    public BaseResponse(){

    }

    public BaseResponse(Integer code){
        this.code = code;
    }

    public BaseResponse(Integer code, T data){
        this.code = code;
        this.data = data;
    }
    public BaseResponse(Integer code, T data,String msg){
        this.code = code;
        this.data = data;
        this.msg = msg;
    }


    public BaseResponse(Integer code, String msg){
        this.code = code;
        this.msg = msg;
    }

    public static BaseResponse success(){
        return new BaseResponse(CODE_SUCCESS);
    }

    public static BaseResponse success(Object data){
        return new BaseResponse(CODE_SUCCESS, data);
    }
    public static BaseResponse success(Object data,String msg){
        return new BaseResponse(CODE_SUCCESS, data,msg);
    }

    public static BaseResponse fail(String msg){
        return new BaseResponse(CODE_FAIL, msg);
    }

    public static BaseResponse error(Integer code,String msg){
        return new BaseResponse(code, msg);
    }

    public static BaseResponse widthCode(Integer errorCode) {
        return new BaseResponse(errorCode);
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

}
