package com.volarpadcloud.api.service;

import com.volarpadcloud.api.entity.SysMenu;
import com.volarpadcloud.api.mapper.SysMenuMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author
 * @email
 * @date 2019/2/15 16:15
 */
@Service
public class SysMenuServiceImpl implements SysMenuService{

    @Resource
    private SysMenuMapper sysMenuMapper;

    @Override
    public List<SysMenu> selectAll() {
        return sysMenuMapper.selectAll();
    }

    @Override
    public void addMenu(SysMenu sysMenu) {
        sysMenuMapper.insert(sysMenu);
    }

    @Override
    public SysMenu selectById(Integer id) {
        SysMenu sysMenu = sysMenuMapper.selectByPrimaryKey(id);
        return sysMenu;
    }
}
