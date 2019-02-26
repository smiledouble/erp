package com.cxs.sys.service.impl;

import com.cxs.sys.constant.SYSConstant;
import com.cxs.sys.domain.Permission;
import com.cxs.sys.domain.Role;
import com.cxs.sys.mapper.PermissionMapper;
import com.cxs.sys.mapper.RoleMapper;
import com.cxs.sys.service.RoleService;
import com.cxs.sys.utils.DataGridView;
import com.cxs.sys.utils.TreeNode;
import com.cxs.sys.vo.RoleVo;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author:chenxiaoshuang
 * @Date:2019/2/23 11:49
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private PermissionMapper permissionMapper;

    @Override
    public DataGridView queryAllRole(RoleVo roleVo) {
        Page<Object> page = null;
        List<Role> roles = null;
        page = PageHelper.startPage(roleVo.getPage(), roleVo.getLimit());

        roles = this.roleMapper.queryAllRole(roleVo);
        if (page.size() == 0) {
            if (roleVo.getPage() == 1) {
                return new DataGridView(page.getTotal(), roles);
            } else {
                //说明当前页的最后一条数据被删掉了 需要查询他的前一页
                page = PageHelper.startPage(roleVo.getPage() - 1, roleVo.getLimit());
                roles = this.roleMapper.queryAllRole(roleVo);
            }
        }
        return new DataGridView(page.getTotal(), roles);
    }

    @Override
    public Integer addRole(RoleVo roleVo) {
        return this.roleMapper.insertSelective(roleVo);
    }

    @Override
    public Integer updateRole(RoleVo roleVo) {
        return this.roleMapper.updateByPrimaryKeySelective(roleVo);
    }

    @Override
    public Integer deleteRole(Integer id) {
        return this.roleMapper.deleteByPrimaryKey(id);
    }

    /**
     * 构造角色和权限复选是的方法
     *
     * @param roleVo
     * @return
     */
    @Override
    public List<TreeNode> loadRolePermissionTreeJson(RoleVo roleVo) {
        //查询全部的菜单和权限
        Permission permission = new Permission();
        permission.setAvailable(SYSConstant.SYS_AVAILABLE_TRUE);
        List<Permission> allPermission = this.permissionMapper.queryAllPermission(permission);
        //查询当前角色拥有的菜单和权限
        List<Permission> currentPermission = this.permissionMapper.queryPermissionByRoleId(roleVo.getId());
        //构造 List<TreeNode>
        List<TreeNode> nodes = new ArrayList<>();
        for (Permission p1 : allPermission) {
            Boolean checked = false;
            for (Permission p2 : currentPermission) {
                if (p1.getId().intValue() == p2.getId().intValue()) {
                    checked = true;
                    break;
                }
            }
            Boolean isParent = p1.getType() .equals(SYSConstant.MENU_TYPE_MENU)  ? true : false;
            Boolean open = p1.getOpen() == SYSConstant.MENU_OPEN_TRUE ? true : false;
            nodes.add(new TreeNode(p1.getId(), p1.getPid(), p1.getName(), isParent, open, checked));
        }
        return nodes;
    }

    /**
     * 保存角色和权限的关系
     *
     * @param roleVo
     * @return
     */
    @Override
    public Integer saveRolePermission(RoleVo roleVo) {
        //先删  在保存
        Integer rid = roleVo.getId();
        Integer[] ids = roleVo.getIds();
       Integer i = this.roleMapper.deletePermissionByRid(rid);
        if(null!=ids && ids.length>0){
            //在添加
            for (Integer pid:ids) {
                this.roleMapper.addRolePermissionByPid(rid,pid);
            }
        }
        return SYSConstant.ROLE_AVAILABLE_TRUE;
    }
}
