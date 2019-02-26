package com.cxs.sys.service;

import com.cxs.sys.domain.Permission;
import com.cxs.sys.utils.DataGridView;
import com.cxs.sys.vo.PermissionVo;

import java.util.List;

/**
 * @Author:chenxiaoshuang
 * @Date:2019/2/20 15:25
 */
public interface PermissionService {

    //全查询主页菜单的方法
    List<Permission> getAllPermission(PermissionVo permissionVo);

    //根据登录用户查询菜单的方法
    List<Permission> getPermissionByUserId(Integer userId);

    //查询当前用户的菜单和权限 forlist
    List<String> getUserPermissionStrForList(Integer userId);

    //分页查询菜单的方法
    DataGridView queryAllMenu(PermissionVo permissionVo);

    //全查询菜单管理里面的所有菜单的方法
    List<Permission> queryAllMenuForList(PermissionVo permissionVo);

    //获得做大的排序码
    Integer getMaxOrdernum();

    //添加菜单的方法
    Integer addMenu(PermissionVo permissionVo);

    Integer updateMenu(PermissionVo permissionVo);

    //查询改菜单有没有子节点
    Integer queryMenuHasChild(Integer id);

    //删除菜单
    Integer deleteMenu(PermissionVo permissionVo);
}
