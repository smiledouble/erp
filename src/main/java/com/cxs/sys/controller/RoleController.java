package com.cxs.sys.controller;

import com.cxs.sys.constant.BaseException;
import com.cxs.sys.constant.BaseResult;
import com.cxs.sys.constant.ResultCode;
import com.cxs.sys.constant.SYSConstant;
import com.cxs.sys.service.RoleService;
import com.cxs.sys.utils.DataGridView;
import com.cxs.sys.utils.TreeNode;
import com.cxs.sys.vo.RoleVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @Author:chenxiaoshuang
 * @Date:2019/2/20 21:04
 */
@RestController
public class RoleController {

    private static final String MODEL = "role";

    @Autowired
    private RoleService roleService;

    /**
     * 加载系统角色
     *
     * @param roleVo
     * @return
     */
    @GetMapping(MODEL + "/loadAllRole")
    public DataGridView loadAllRole(RoleVo roleVo) {
        if (null == roleVo) {
            throw new BaseException(ResultCode.FAIL);
        }
        return this.roleService.queryAllRole(roleVo);
    }

    /**
     * 添加
     *
     * @param roleVo
     * @return
     */

    @PostMapping(MODEL + "/addRole")
    public BaseResult<?> addRole(RoleVo roleVo) {
        String msg = "";
        if (roleVo == null) {
            throw new BaseException(ResultCode.FAIL);
        }
        try {
            roleVo.setAvailable(SYSConstant.ROLE_AVAILABLE_TRUE);
            Integer i = this.roleService.addRole(roleVo);
            msg = i > 0 ? SYSConstant.OPERATE_ADD_SUCCESS : SYSConstant.OPERATE_ADD_ERROR;
            return BaseResult.success(ResultCode.valueOf(ResultCode.SUCCESS.getCode()), msg);
        } catch (BaseException e) {
            msg = SYSConstant.OPERATE_ADD_ERROR;
        }
        return BaseResult.error(ResultCode.valueOf(ResultCode.FAIL.getCode()), msg);

    }

    /**
     * 修改角色
     *
     * @param roleVo
     * @return
     */

    @PostMapping(MODEL + "/updateRole")
    public BaseResult<?> updateRole(RoleVo roleVo) {
        String msg = "";
        if (roleVo == null) {
            throw new BaseException(ResultCode.FAIL);
        }
        try {
            Integer i = this.roleService.updateRole(roleVo);
            msg = i > 0 ? SYSConstant.OPERATE_UPDATE_SUCCESS : SYSConstant.OPERATE_UPDATE_ERROR;
            return BaseResult.success(ResultCode.valueOf(ResultCode.SUCCESS.getCode()), msg);
        } catch (BaseException e) {
            msg = SYSConstant.OPERATE_UPDATE_ERROR;
        }
        return BaseResult.error(ResultCode.valueOf(ResultCode.FAIL.getCode()), msg);
    }

    /**
     * 删除角色
     *
     * @param roleVo
     * @return
     */
    @PostMapping(MODEL + "/deleteRole")
    public BaseResult<?> deleteRole(RoleVo roleVo) {
        String msg = "";
        if (roleVo == null) {
            throw new BaseException(ResultCode.FAIL);
        }
        try {
            Integer i = this.roleService.deleteRole(roleVo.getId());
            msg = i > 0 ? SYSConstant.OPERATE_DELETE_SUCCESS : SYSConstant.OPERATE_DELETE_ERROR;
            return BaseResult.success(ResultCode.valueOf(ResultCode.SUCCESS.getCode()), msg);
        } catch (BaseException e) {
            msg = SYSConstant.OPERATE_DELETE_ERROR;
        }
        return BaseResult.error(ResultCode.valueOf(ResultCode.FAIL.getCode()), msg);
    }

    /**
     * 加载角色和权限复选树的方法
     *
     * @param roleVo
     * @return
     */
    @PostMapping(MODEL + "/loadRolePermissionTree")
    public List<TreeNode> loadRolePermissionTree(RoleVo roleVo) {
        if (roleVo == null) {
            throw new BaseException(ResultCode.FAIL);
        }
        return this.roleService.loadRolePermissionTreeJson(roleVo);
    }

    /**
     * 保存角色和权限的关系
     *
     * @param roleVo
     * @return
     */
    @PostMapping(MODEL + "/saveRolePermission")
    public BaseResult<?> saveRolePermission(RoleVo roleVo) {
        String msg = "";
        if (roleVo == null) {
            throw new BaseException(ResultCode.FAIL);
        }
        try {
        Integer i= this.roleService.saveRolePermission(roleVo);
        msg = i > 0 ? SYSConstant.OPERATE_DISPATCH_SUCCESS : SYSConstant.OPERATE_DISPATCH_ERROR;
        return BaseResult.success(ResultCode.valueOf(ResultCode.SUCCESS.getCode()), msg);
    } catch (BaseException e) {
        msg = SYSConstant.OPERATE_DISPATCH_ERROR;
    }
        return BaseResult.error(ResultCode.valueOf(ResultCode.FAIL.getCode()), msg);
    }

}
