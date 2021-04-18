package com.volarpadcloud.api.entity.enums;

import lombok.Getter;

/**
 * @author
 * @email
 * @date 2019/1/25 15:33
 */
@Getter
public enum ResponseCode {

    SUCCESS(200,"成功"),
    USERERROR(201,"账号或密码不正确"),
    ERROR(505,"错误");

    private Integer code;
    private String message;
    ResponseCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
