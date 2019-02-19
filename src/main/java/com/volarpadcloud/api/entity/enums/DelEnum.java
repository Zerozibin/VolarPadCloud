package com.volarpadcloud.api.entity.enums;

import lombok.Getter;

/**
 * @author linzibin
 * @email Zerozibin@163.com
 * @application
 */
@Getter
public enum DelEnum {
    //项目中的是否删除字段枚举，0为未删除
    VOLARPAD_ISDEL(0,"未删除"),
    //项目中的是否删除字段，1为已删除
    VOLARPAD_NOTDEL(1,"删除");


    private Integer code;
    private String message;
    DelEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
