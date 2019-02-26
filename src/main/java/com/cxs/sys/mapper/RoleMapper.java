package com.cxs.sys.mapper;

import com.cxs.sys.domain.Role;

import java.util.List;

public interface RoleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Role record);

    int insertSelective(Role record);

    Role selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);

    //全查询角色
    List<Role> queryAllRole(Role role);

    //根据rid 删除角色对应的权限
    Integer deletePermissionByRid(Integer id);

    //添加角色和权限
    void addRolePermissionByPid(Integer rid, Integer pid);

    //删除用户和角色之间的关系
    Integer deleteRoleByUserId(Integer id);

    //根据uid查询所有的角色
    List<Role> queryRoleByUserId(Integer id);
}