package com.volarpadcloud.api.entity.enums;

import lombok.Getter;

/**
 * @author
 * @email
 * @date 2019/1/25 15:21
 */
@Getter
public enum StatusCodeEnum {

    //状态码，200正常
    RESPONSE_STATUS_NORMAL(200,"200","正常"),
    //状态码，505错误
    RESPONSE_STATUS_ERROR(505,"505","异常");


    private Integer intCode;
    private String stringCode;
    private String message;
    StatusCodeEnum(Integer intCode,String stringCode, String message) {
        this.intCode = intCode;
        this.stringCode = stringCode;
        this.message = message;
    }
}
