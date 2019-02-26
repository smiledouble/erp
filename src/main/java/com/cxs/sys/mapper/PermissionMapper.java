package com.cxs.sys.mapper;

import com.cxs.sys.domain.Permission;

import java.util.List;

public interface PermissionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Permission record);

    int insertSelective(Permission record);

    Permission selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Permission record);

    int updateByPrimaryKey(Permission record);

    //查询主页左边的树的方法
    List<Permission> queryAllPermission(Permission permission);

    //根据登录用户的id查询该用户的菜单和权限
    List<Permission> queryAllPermissionByUserId(Integer userId);

    //查询菜单管理里面的方法
    List<Permission> queryAllMenu(Permission permission);

    //获得做大的排序码
    Integer queryMaxOrdernum();

    //查看是否有子菜单
    Integer queryMenuHasChild(Integer id);

    //根据角色id查询当前拥有的权限
    List<Permission> queryPermissionByRoleId(Integer id);
}