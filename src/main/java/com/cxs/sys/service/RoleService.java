package com.cxs.sys.service;

import com.cxs.sys.utils.DataGridView;
import com.cxs.sys.utils.TreeNode;
import com.cxs.sys.vo.RoleVo;

import java.util.List;

/**
 * @Author:chenxiaoshuang
 * @Date:2019/2/20 20:57
 */
public interface RoleService {

    DataGridView queryAllRole(RoleVo roleVo);

    Integer addRole(RoleVo roleVo);

    Integer updateRole(RoleVo roleVo);

    Integer deleteRole(Integer id);

    /**
     * 构造角色和权限复选是的方法
     * @param roleVo
     * @return
     */
    List<TreeNode> loadRolePermissionTreeJson(RoleVo roleVo);

    /**
     * 保存角色和权限的关系
     * @param roleVo
     * @return
     */
    Integer saveRolePermission(RoleVo roleVo);
}
