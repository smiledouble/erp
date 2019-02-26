package com.cxs.sys.constant;

/**
 * @Author:chenxiaoshuang
 * @Date:2019/2/20 11:06
 */
public interface SYSConstant {

    //超级管理员
    Integer USER_TYPE_SUPER = 0;
    //普通用户
    Integer USER_TYPE_NORMAL = 1;

    String USER_PWD_DEFAULT = "123456";


    Integer PWD_SANLIE = 2;

    //表中的类型是否为菜单
    String MENU_TYPE_MENU = "menu";
    //表中的类型是否为权限
    String PERMISSION_TYPE_MENU = "permission";
    //菜单是否可用
    Integer SYS_AVAILABLE_TRUE = 1;
    //菜单是否展开
    Integer MENU_OPEN_TRUE = 1;
    //菜单是否父节点
    Integer MENU_PARENT_TRUE = 1;
    Integer MENU_PARENT_FALSE = 0;


    //操作删除成功
    String OPERATE_DELETE_SUCCESS = "删除成功";
    //操作删除失败
    String OPERATE_DELETE_ERROR = "删除失败";

    String OPERATE_ADD_SUCCESS = "添加成功";
    String OPERATE_ADD_ERROR = "添加失败";

    String LOGIN_ERROR_PWD = "密码不正确";
    String LOGIN_ERROR_NAME = "用户名不存在";

    String OPERATE_UPDATE_SUCCESS = "修改成功";
    String OPERATE_UPDATE_ERROR = "修改失败";
    //部门是否父节点
    Integer DEPT_PARENT_TRUE = 1;
    Integer DEPT_PARENT_FALSE = 0;
    //部门节点是否展开
    Integer DEPT_OPEN_TRUE = 1;
    Integer DEPT_OPEN_FALSE = 0;


    String DEPT_CHILD_TRUE = "该部门有子部门，请先删除子部门";
    String MENU_CHILD_TRUE = "该菜单有子菜单或者权限节点，请先删除子菜单或子权限";

    //权限可用
    Integer PERMISSION_AVAILABLE_MENU = 1;
    //角色可用
    Integer ROLE_AVAILABLE_TRUE = 1;

    //给角色分配权限
    String OPERATE_DISPATCH_SUCCESS = "分配成功";
    String OPERATE_DISPATCH_ERROR = "分配失败";
    String PWD_RESET_SUCCESS = "重置成功";
    String PWD_RESET_ERROR = "重置失败";
    String PWD_CHANGE_SUCCESS = "密码修改成功哦，请尽快重新登录呢！亲";
    String PWD_CHANGE_ERROR = "修改密码失败";
    String PWD_OLD_ERROR = "原始密码错误哦";
}
