package com.volarpadcloud.api.controller;

import com.volarpadcloud.api.base.BaseResponse;
import com.volarpadcloud.api.service.SysUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * @author
 * @email
 * @date 2019/2/21 9:47
 */
@Controller
public class LoginController {
    private final static Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Resource
    private SysUserService sysUserService;

    @RequestMapping(value = "login")
    public String login(){
        return "login";
    }

    @RequestMapping(value = "checkSysUser",method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse checkSysUser(String username,String password){
        return sysUserService.selectSysUserByUsername(username,password);
    }
}
