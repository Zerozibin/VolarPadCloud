package com.volarpadcloud.api.entity;

import io.swagger.models.auth.In;
import lombok.Data;

import java.util.ArrayList;

/**
 * @author
 * @email
 * @date 2019/2/18 15:51
 */
@Data
public class TreeModel {
    private Integer id;
    private String text; //标题内容
    private String state; //状态 closed-合起、open-打开
    private ArrayList<TreeModel> children;//子节点，子节点有0个或多个，所以用链表存放
    private int level_id;//层级       根层级为1
    private int parent_id; //父节点   根节点为0
    private String flag;

}
