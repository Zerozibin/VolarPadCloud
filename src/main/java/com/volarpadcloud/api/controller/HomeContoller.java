package com.volarpadcloud.api.controller;

import com.volarpadcloud.api.base.BaseResponse;
import com.volarpadcloud.api.entity.SysMenu;
import com.volarpadcloud.api.service.SysMenuService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author
 * @email
 * @date 2019/2/15 16:11
 */
@Controller
public class HomeContoller {

    @Resource
    private SysMenuService sysMenuService;

    @RequestMapping(value = "loadMenu",method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse loadMenu(){
        List<SysMenu> sysMenus =  sysMenuService.selectAll();

        return BaseResponse.success(sysMenus);
    }

}
