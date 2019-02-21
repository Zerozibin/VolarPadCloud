package com.volarpadcloud.api.controller;

import com.volarpadcloud.api.base.BaseResponse;
import com.volarpadcloud.api.entity.SysMenu;
import com.volarpadcloud.api.entity.TreeModel;
import com.volarpadcloud.api.entity.enums.DelEnum;
import com.volarpadcloud.api.entity.enums.StatusEnum;
import com.volarpadcloud.api.service.SysMenuService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author
 * @email
 * @date 2019/2/15 16:11
 */
@Controller
public class HomeController {

    @Resource
    private SysMenuService sysMenuService;

    @RequestMapping(value = "loadMenu",method = RequestMethod.POST)
    @ResponseBody
    public List<TreeModel> loadMenu(){
        List<SysMenu> sysMenus =  sysMenuService.selectAll();

        List<TreeModel> list = new ArrayList<>();
        for (SysMenu sysMenu : sysMenus) {
            TreeModel treeModel = new TreeModel();
            treeModel.setId(sysMenu.getId());
            treeModel.setParent_id(sysMenu.getParentId()==null?0:sysMenu.getParentId());
            treeModel.setState("open");
            treeModel.setText(sysMenu.getName());
            treeModel.setLevel_id(sysMenu.getLevel());
            treeModel.setFlag(sysMenu.getFlag()==null?"":sysMenu.getFlag());
            if(sysMenu.getLevel()==1){
                list.add(treeModel);
            }else{
                for (TreeModel model : list) {
                    if(model.getId().equals(sysMenu.getParentId())){
                        if(model.getChildren()==null){
                            ArrayList<TreeModel> childTree = new ArrayList<>();
                            childTree.add(treeModel);
                            model.setChildren(childTree);
                        }else{
                            ArrayList<TreeModel> children = model.getChildren();
                            children.add(treeModel);
                        }
                    }
                }

            }
        }

        return list;
    }

    @RequestMapping(value = "addMenu",method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse addMenu(String menuNmae){
        SysMenu sysMenu = new SysMenu();
        sysMenu.setName(menuNmae);
        sysMenu.setLevel(1);
        sysMenu.setCreateTime(new Date());
        sysMenu.setUpdateTime(new Date());
        sysMenu.setStatus(StatusEnum.VOLARPAD_STATUS_START.getCode());
        sysMenu.setIsDel(DelEnum.VOLARPAD_ISDEL.getCode());
        sysMenuService.addMenu(sysMenu);
        return BaseResponse.success("添加成功");
    }

}
