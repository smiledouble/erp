package com.cxs.sys.service.impl;

import com.cxs.sys.constant.SYSConstant;
import com.cxs.sys.domain.Dept;
import com.cxs.sys.domain.Permission;
import com.cxs.sys.mapper.PermissionMapper;
import com.cxs.sys.service.PermissionService;
import com.cxs.sys.utils.DataGridView;
import com.cxs.sys.vo.PermissionVo;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author:chenxiaoshuang
 * @Date:2019/2/20 15:27
 */
@Service
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private PermissionMapper permissionMapper;

    /**
     * 得到所偶遇的权限
     * @param permissionVo
     * @return
     */
    @Override
    public List<Permission> getAllPermission(PermissionVo permissionVo) {
        return this.permissionMapper.queryAllPermission(permissionVo);
    }

    @Override
    public List<Permission> getPermissionByUserId(Integer userid) {
        //根据登录的用户的id查询所有的菜单和权限
        //Permission permissionVo = new Permission();
        return this.permissionMapper.queryAllPermissionByUserId(userid);
    }

    /**
     * 根据当前用户的id查询他的权限 并且放到集合中 shiro的myrealm要用
     * @param userId
     * @return
     */
    @Override
    public List<String> getUserPermissionStrForList(Integer userId) {
        List<Permission> list = this.permissionMapper.queryAllPermissionByUserId(userId);
        List<String> percodes = new ArrayList<>();

        list.forEach((p)-> percodes.add(p.getPercode()));

        return percodes;
    }

    /**
     * 分页查询菜单管理里面的方法
     *
     * @param permissionVo
     * @return
     */
    @Override
    public DataGridView queryAllMenu(PermissionVo permissionVo) {
        Page<Object> page = null;
        List<Permission> permissions = null;
        page = PageHelper.startPage(permissionVo.getPage(), permissionVo.getLimit());

        permissions = this.permissionMapper.queryAllMenu(permissionVo);
        if (page.size() == 0) {
            if (permissionVo.getPage() == 1) {
                return new DataGridView(page.getTotal(), permissions);
            } else {
                //说明当前页的最后一条数据被删掉了 需要查询他的前一页
                page = PageHelper.startPage(permissionVo.getPage() - 1, permissionVo.getLimit());
                permissions = this.permissionMapper.queryAllMenu(permissionVo);
            }
        }
        return new DataGridView(page.getTotal(), permissions);
    }

    /**
     * 全查询菜单的方法
     *
     * @param permissionVo
     * @return
     */
    @Override
    public List<Permission> queryAllMenuForList(PermissionVo permissionVo) {
        return this.permissionMapper.queryAllMenu(permissionVo);
    }

    @Override
    public Integer getMaxOrdernum() {
        return this.permissionMapper.queryMaxOrdernum();
    }

    /**
     * 添加菜单的方法
     *
     * @param permissionVo
     * @return
     */
    @Override
    public Integer addMenu(PermissionVo permissionVo) {
        Integer i = this.permissionMapper.insertSelective(permissionVo);
        //修改它的上一级的是否为父节点
        Permission permission = new Permission();
        permission.setId(permissionVo.getPid());
        permission.setParent(SYSConstant.MENU_PARENT_TRUE);
        this.permissionMapper.updateByPrimaryKeySelective(permission);
        return i;
    }

    @Override
    public Integer updateMenu(PermissionVo permissionVo) {
        return this.permissionMapper.updateByPrimaryKeySelective(permissionVo);
    }

    /**
     * 判断有没有子菜单的方法
     *
     * @param id
     * @return
     */
    @Override
    public Integer queryMenuHasChild(Integer id) {
        return this.permissionMapper.queryMenuHasChild(id);
    }

    /**
     * 删除的方法
     *
     * @param permissionVo
     * @return
     */
    @Override
    public Integer deleteMenu(PermissionVo permissionVo) {
        Integer i = this.permissionMapper.deleteByPrimaryKey(permissionVo.getId());
        //调用下面的根据节点id 查询有没有子节点
        Integer count = this.permissionMapper.queryMenuHasChild(permissionVo.getPid());
        if (count < 1 && permissionVo.getPid() != 1) {
            //说明没有子节点了 修改把他parent改成0
            Permission permission = new Permission();
            permission.setId(permissionVo.getPid());
            permission.setParent(SYSConstant.MENU_PARENT_FALSE);
            this.permissionMapper.updateByPrimaryKeySelective(permission);
        }
        return i;
    }


}
