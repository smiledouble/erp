package com.cxs.sys.controller;

import com.cxs.sys.constant.BaseException;
import com.cxs.sys.constant.BaseResult;
import com.cxs.sys.constant.ResultCode;
import com.cxs.sys.constant.SYSConstant;
import com.cxs.sys.service.PermissionService;
import com.cxs.sys.utils.DataGridView;
import com.cxs.sys.vo.PermissionVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author:chenxiaoshuang
 * @Date:2019/2/20 15:29
 */
@RestController
public class PermissionController {


    private static final String MODEL = "permission";

    @Autowired
    private PermissionService permissionService;


    /**
     * 加载右边的数据表格
     *
     * @param permissionVo
     * @return
     */
    @GetMapping(MODEL + "/loadAllPermission")
    public DataGridView loadAllPermission(PermissionVo permissionVo) {
        permissionVo.setType(SYSConstant.PERMISSION_TYPE_MENU);
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
    @PostMapping(MODEL + "/addPermission")
    public BaseResult<?> addPermission(PermissionVo permissionVo) {
        String msg = "";
        if (permissionVo == null) {
            throw new BaseException(ResultCode.FAIL);
        }
        try {
            permissionVo.setType(SYSConstant.PERMISSION_TYPE_MENU);
            permissionVo.setAvailable(SYSConstant.PERMISSION_AVAILABLE_MENU);

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
    @PostMapping(MODEL + "/updatePermission")
    public BaseResult<?> updatePermission(PermissionVo permissionVo) {
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
     * 判断该权限是否有子权限
     *
     * @param PermissionVo
     * @return
     */
    @PostMapping(MODEL + "/thisPermissionHasChild")
    public BaseResult<?> thisPermissionHasChild(PermissionVo permissionVo) {
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
     * 删除权限
     *
     * @param PermissionVo
     * @return
     */
    @PostMapping(MODEL + "/deletePermission")
    public BaseResult<?> deletePermission(PermissionVo permissionVo) {
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
