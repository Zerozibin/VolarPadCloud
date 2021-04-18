package com.volarpadcloud.api.entity.enums;

import lombok.Getter;

/**
 * @author linzibin
 * @email Zerozibin@163.com
 * @application
 */
@Getter
public enum StatusEnum {

    //项目中的状态字段枚举，0为初始化
    VOLARPAD_STATUS_INITIALIZE(0,"初始化"),
    //项目中的状态字段枚举，1为启用
    VOLARPAD_STATUS_START(1,"启用"),
    //项目中的状态字段枚举，2为禁用
    VOLARPAD_STATUS_FORBIDDEN(2,"禁用");


    private Integer code;
    private String message;
    StatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

}
