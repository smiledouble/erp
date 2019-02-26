package com.cxs.sys.controller;

import com.cxs.sys.constant.BaseException;
import com.cxs.sys.constant.BaseResult;
import com.cxs.sys.constant.ResultCode;
import com.cxs.sys.constant.SYSConstant;
import com.cxs.sys.domain.Permission;
import com.cxs.sys.domain.User;
import com.cxs.sys.service.PermissionService;
import com.cxs.sys.utils.DataGridView;
import com.cxs.sys.utils.TreeNode;
import com.cxs.sys.utils.TreeNodeBulider;
import com.cxs.sys.utils.WebUtils;
import com.cxs.sys.vo.PermissionVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author:chenxiaoshuang
 * @Date:2019/2/20 15:29
 */
@RestController
public class MenuController {


    private static final String MODEL = "menu";

    @Autowired
    private PermissionService permissionService;

    /**
     * 加载主页左边的树结构
     *
     * @param permissionVo
     * @return
     */
    @GetMapping(MODEL + "/loadIndexLeftTree")
    public List<TreeNode> loadIndexLeftTree(PermissionVo permissionVo) {
        List<Permission> permissions = null;
        List<TreeNode> nodes = new ArrayList<>();
        //设置属性
        permissionVo.setType(SYSConstant.MENU_TYPE_MENU);
        permissionVo.setAvailable(SYSConstant.SYS_AVAILABLE_TRUE);
        //得到当前用户
        User user = WebUtils.getCurrentUser();

        if (user.getType().intValue() == SYSConstant.USER_TYPE_SUPER) {
            permissions = this.permissionService.getAllPermission(permissionVo);
        } else {
            permissions = this.permissionService.getPermissionByUserId(user.getId());
        }
        for (Permission p : permissions) {
            Integer id = p.getId();
            Integer pid = p.getPid();
            String title = p.getName();
            String icon = p.getIcon();
            String href = p.getHref();
            Boolean spread = p.getOpen() == SYSConstant.MENU_OPEN_TRUE ? true : false;
            nodes.add(new TreeNode(id, pid, title, href, icon, spread));
        }
        return TreeNodeBulider.buildTd(nodes, 1);
    }

    /**
     * 加载菜单管理左边的树
     *
     * @param permissionVo
     * @return
     */
    @PostMapping(MODEL + "/loadMenuLeftTreeJson")
    public List<TreeNode> loadMenuLeftTreeJson(PermissionVo permissionVo) {
        if (permissionVo == null) {
            throw new BaseException(ResultCode.FAIL);
        }
        List<TreeNode> nodes = new ArrayList<>();
        permissionVo.setType(SYSConstant.MENU_TYPE_MENU);
        List<Permission> permissions = this.permissionService.queryAllMenuForList(permissionVo);
        for (Permission permission : permissions) {
            Boolean isParent = permission.getParent() == SYSConstant.MENU_PARENT_TRUE ? true : false;
            Boolean open = permission.getOpen() == SYSConstant.MENU_OPEN_TRUE ? true : false;
            nodes.add(new TreeNode(permission.getId(), permission.getPid(), permission.getName(), isParent, open));
        }
        return nodes;
    }

    /**
     * 加载右边的数据表格
     *
     * @param permissionVo
     * @return
     */
    @GetMapping(MODEL + "/loadAllMenu")
    public DataGridView loadAllMenu(PermissionVo permissionVo) {
        permissionVo.setType(SYSConstant.MENU_TYPE_MENU);
        return this.permissionService.queryAllMenu(permissionVo);
    }

    /**
     * 找到最大的排序码 +1
     *
     * @return
     */
    @GetMapping(MODEL + "/getMaxOrderNumber")
    public BaseResult<?> getMaxOrderNumber() {
        Integer maxOrder = this.permissionService.getMaxOrdernum();
        return BaseResult.success(maxOrder + 1);
    }

    /**
     * 添加菜单的方法
     *
     * @param permissionVo
     * @return
     */
    @PostMapping(MODEL + "/addMenu")
    public BaseResult<?> addMenu(PermissionVo permissionVo) {
        String msg = "";
        if (permissionVo == null) {
            throw new BaseException(ResultCode.FAIL);
        }
        try {
            permissionVo.setType(SYSConstant.MENU_TYPE_MENU);
            Integer i = this.permissionService.addMenu(permissionVo);
            msg = i > 0 ? SYSConstant.OPERATE_ADD_SUCCESS : SYSConstant.OPERATE_ADD_ERROR;
            return BaseResult.success(ResultCode.valueOf(ResultCode.SUCCESS.getCode()), msg);
        } catch (BaseException e) {
            msg = SYSConstant.OPERATE_ADD_ERROR;
        }
        return BaseResult.error(ResultCode.valueOf(ResultCode.FAIL.getCode()), msg);
    }


    /**
     * 修改
     *
     * @param permissionVo
     * @return
     */
    @PostMapping(MODEL + "/updateMenu")
    public BaseResult<?> updateMenu(PermissionVo permissionVo) {
        String msg = "";
        if (permissionVo == null) {
            throw new BaseException(ResultCode.FAIL);
        }
        try {
            Integer i = this.permissionService.updateMenu(permissionVo);
            msg = i > 0 ? SYSConstant.OPERATE_UPDATE_SUCCESS : SYSConstant.OPERATE_UPDATE_ERROR;
            return BaseResult.success(ResultCode.valueOf(ResultCode.SUCCESS.getCode()), msg);
        } catch (BaseException e) {
            msg = SYSConstant.OPERATE_UPDATE_ERROR;
        }
        return BaseResult.error(ResultCode.valueOf(ResultCode.FAIL.getCode()), msg);
    }


    /**
     * 判断该菜单是否有子菜单
     *
     * @param PermissionVo
     * @return
     */
    @PostMapping(MODEL + "/thisMenuHasChild")
    public BaseResult<?> thisMenuHasChild(PermissionVo permissionVo) {
        if (permissionVo == null) {
            throw new BaseException(ResultCode.FAIL);
        }
        Map<String, Object> map = new HashMap<>();
        Integer child = this.permissionService.queryMenuHasChild(permissionVo.getId());
        map.put("flag", child > 0 ? true : false);
        map.put("message", SYSConstant.MENU_CHILD_TRUE);
        return BaseResult.success(map);
    }

    /**
     * 删除菜单
     *
     * @param PermissionVo
     * @return
     */
    @PostMapping(MODEL + "/deleteMenu")
    public BaseResult<?> deleteMenu(PermissionVo permissionVo) {
        String msg = "";
        if (permissionVo == null) {
            throw new BaseException(ResultCode.FAIL);
        }
        try {
            Integer i = this.permissionService.deleteMenu(permissionVo);
            msg = i > 0 ? SYSConstant.OPERATE_DELETE_SUCCESS : SYSConstant.OPERATE_DELETE_ERROR;
            return BaseResult.success(ResultCode.valueOf(ResultCode.SUCCESS.getCode()), msg);
        } catch (BaseException e) {
            msg = SYSConstant.OPERATE_DELETE_ERROR;
        }
        return BaseResult.error(ResultCode.valueOf(ResultCode.FAIL.getCode()), msg);
    }

}
