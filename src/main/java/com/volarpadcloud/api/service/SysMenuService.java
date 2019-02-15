package com.volarpadcloud.api.service;

import com.volarpadcloud.api.entity.SysMenu;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author
 * @email
 * @date 2019/2/15 16:14
 */
public interface SysMenuService {
    List<SysMenu> selectAll();
}
