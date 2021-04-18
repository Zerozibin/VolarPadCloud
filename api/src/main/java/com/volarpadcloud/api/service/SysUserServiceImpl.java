package com.volarpadcloud.api.service;

import com.volarpadcloud.api.base.BaseResponse;
import com.volarpadcloud.api.entity.SysUser;
import com.volarpadcloud.api.entity.enums.ResponseCode;
import com.volarpadcloud.api.mapper.SysUserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author
 * @email
 * @date 2019/2/21 9:53
 */
@Service
public class SysUserServiceImpl implements SysUserService {
    private final static Logger logger = LoggerFactory.getLogger(SysMenuService.class);

    @Resource
    private SysUserMapper sysUserMapper;

    @Override
    public BaseResponse selectSysUserByUsername(String username, String password) {
        logger.info("校验账号:{},密码:{}",username,password);
        try{
            SysUser sysUser = sysUserMapper.selectOneByUsername(username);
            if(sysUser!=null && password.equals(sysUser.getPassword())){
                return BaseResponse.success("登录成功");
            }
            return BaseResponse.error(ResponseCode.USERERROR.getCode(),ResponseCode.USERERROR.getMessage());
        }catch (Exception e){
            logger.info("系统异常:{}",e);
            return BaseResponse.error(ResponseCode.ERROR.getCode(),ResponseCode.ERROR.getMessage());
        }
    }
}
