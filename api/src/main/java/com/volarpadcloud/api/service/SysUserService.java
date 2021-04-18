package com.volarpadcloud.api.service;

import com.volarpadcloud.api.base.BaseResponse;

/**
 * @author
 * @email
 * @date 2019/2/21 9:51
 */
public interface SysUserService {
    BaseResponse selectSysUserByUsername(String username, String password);
}
