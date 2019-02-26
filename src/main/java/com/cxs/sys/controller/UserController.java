package com.cxs.sys.controller;

import com.cxs.sys.constant.BaseException;
import com.cxs.sys.constant.BaseResult;
import com.cxs.sys.constant.ResultCode;
import com.cxs.sys.constant.SYSConstant;
import com.cxs.sys.domain.User;
import com.cxs.sys.service.UserService;
import com.cxs.sys.utils.DataGridView;
import com.cxs.sys.utils.Md5Utils;
import com.cxs.sys.utils.PinyinUtils;
import com.cxs.sys.utils.WebUtils;
import com.cxs.sys.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author:chenxiaoshuang
 * @Date:2019/2/23 19:05
 */
@RestController
public class UserController {

    private static final String MODEL = "user";

    @Autowired
    private UserService userService;

    /**
     * 分页数据表格
     *
     * @param userVo
     * @return
     */
    @GetMapping(MODEL + "/loadAllUser")
    public DataGridView loadAllUser(UserVo userVo) {
        return this.userService.queryAllUser(userVo);
    }


    /**
     * 汉字转拼音
     *
     * @param userVo
     * @return
     */
    @PostMapping(MODEL + "/nameConventToPinying")
    public BaseResult<?> nameConventToPinying(UserVo userVo) {
        Map<String, Object> map = new HashMap<>();
        map.put("value", PinyinUtils.toPinyin(userVo.getName()));
        return BaseResult.success(map);
    }

    /**
     * 根据部门id 查询该部门下面的所有员工
     *
     * @param userVo
     * @return
     */
    @PostMapping(MODEL + "/loadUserByDeptId")
    public BaseResult<?> loadUserByDeptId(UserVo userVo) {
        if (userVo == null) {
            throw new BaseException(ResultCode.FAIL);
        }
        List<User> list = this.userService.queryUserByDeptId(userVo);
        return BaseResult.success(list);
    }

    /**
     * 查询最大的排序码
     *
     * @return
     */
    @GetMapping(MODEL + "/getMaxOrderNumber")
    public BaseResult<?> getMaxOrderNumber() {
        Integer value = this.userService.getMaxOrderNumber();
        Map<String, Object> map = new HashMap<>();
        map.put("value", value + 1);
        return BaseResult.success(map);
    }

    /**
     * 添加用户的方法
     *
     * @param userVo
     * @return
     */
    @PostMapping(MODEL + "/addUser")
    public BaseResult<?> addUser(UserVo userVo) {
        String msg = "";
        if (userVo == null) {
            throw new BaseException(ResultCode.FAIL);
        }
        try {
            //设置盐
            String salt = Md5Utils.createRandomStr();
            String pwd = Md5Utils.md5(SYSConstant.USER_PWD_DEFAULT, salt, SYSConstant.PWD_SANLIE);
            userVo.setType(SYSConstant.USER_TYPE_NORMAL);
            Integer i = this.userService.addUser(userVo);
            msg = i > 0 ? SYSConstant.OPERATE_ADD_SUCCESS : SYSConstant.OPERATE_ADD_ERROR;
            return BaseResult.success(ResultCode.valueOf(ResultCode.SUCCESS.getCode()), msg);
        } catch (BaseException e) {
            msg = SYSConstant.OPERATE_ADD_ERROR;
        }
        return BaseResult.error(ResultCode.valueOf(ResultCode.FAIL.getCode()), msg);
    }

    /**
     * 根据用户的领导的mgr 当做用户的id 查询用户对象
     *
     * @param userVo
     * @return
     */
    @PostMapping(MODEL + "/loadUserByUserId")
    public BaseResult<?> loadUserByUserId(UserVo userVo) {
        if (userVo == null) {
            throw new BaseException(ResultCode.FAIL);
        }
        User user = this.userService.queryUserByUserId(userVo);
        if (null == user) {
            return BaseResult.error(new User());
        }
        return BaseResult.success(user);
    }


    /**
     * 修改的方法
     *
     * @param userVo
     * @return
     */
    @PostMapping(MODEL + "/updateUser")
    public BaseResult<?> updateUser(UserVo userVo) {
        String msg = "";
        if (userVo == null) {
            throw new BaseException(ResultCode.FAIL);
        }
        try {
            Integer i = this.userService.updateUser(userVo);
            msg = i > 0 ? SYSConstant.OPERATE_UPDATE_SUCCESS : SYSConstant.OPERATE_UPDATE_ERROR;
            return BaseResult.success(ResultCode.valueOf(ResultCode.SUCCESS.getCode()), msg);
        } catch (BaseException e) {
            msg = SYSConstant.OPERATE_UPDATE_ERROR;
        }
        return BaseResult.error(ResultCode.valueOf(ResultCode.FAIL.getCode()), msg);
    }

    /**
     * 重置密码
     *
     * @param userVo
     * @return
     */
    @PostMapping(MODEL + "/resetPwd")
    public BaseResult<?> resetPwd(UserVo userVo) {
        String msg = "";
        if (userVo == null) {
            throw new BaseException(ResultCode.FAIL);
        }
        try {
            Integer i = this.userService.resetPwd(userVo);
            msg = i > 0 ? SYSConstant.PWD_RESET_SUCCESS : SYSConstant.PWD_RESET_ERROR;
            return BaseResult.success(ResultCode.valueOf(ResultCode.SUCCESS.getCode()), msg);
        } catch (BaseException e) {
            msg = SYSConstant.PWD_RESET_ERROR;
        }
        return BaseResult.error(ResultCode.valueOf(ResultCode.FAIL.getCode()), msg);

    }

    /**
     * 删除用户
     *
     * @param userVo
     * @return
     */
    @PostMapping(MODEL + "/deleteUser")
    public BaseResult<?> deleteUser(UserVo userVo) {
        String msg = "";
        if (userVo == null) {
            throw new BaseException(ResultCode.FAIL);
        }
        try {
            Integer i = this.userService.deleteUser(userVo.getId());
            msg = i > 0 ? SYSConstant.OPERATE_DELETE_SUCCESS : SYSConstant.OPERATE_DELETE_ERROR;
            return BaseResult.success(ResultCode.valueOf(ResultCode.SUCCESS.getCode()), msg);
        } catch (BaseException e) {
            msg = SYSConstant.OPERATE_DELETE_ERROR;
        }
        return BaseResult.error(ResultCode.valueOf(ResultCode.FAIL.getCode()), msg);
    }


    /**
     * 查询用户拥有的角色
     *
     * @param userVo
     * @return
     */
    @GetMapping(MODEL + "/loadAllRoleByUserId")
    public DataGridView loadAllRoleByUserId(UserVo userVo) {
        return this.userService.loadUserRole(userVo);
    }


    /**
     * 保存用户和角色的关系
     *
     * @param userVo
     * @return
     */
    @PostMapping(MODEL + "/saveRoleUser")
    public BaseResult<?> saveRoleUser(UserVo userVo) {
        String msg = "";
        if (userVo == null) {
            throw new BaseException(ResultCode.FAIL);
        }
        try {
            Integer i = this.userService.saveRoleUser(userVo);
            msg = i > 0 ? SYSConstant.OPERATE_DISPATCH_SUCCESS : SYSConstant.OPERATE_DISPATCH_ERROR;
            return BaseResult.success(ResultCode.valueOf(ResultCode.SUCCESS.getCode()), msg);
        } catch (BaseException e) {
            msg = SYSConstant.OPERATE_DISPATCH_ERROR;
        }
        return BaseResult.error(ResultCode.valueOf(ResultCode.FAIL.getCode()), msg);
    }


    /**
     * 修改密码
     *
     * @param userVo
     * @return
     */
    @PostMapping(MODEL + "/updatePwd")
    public BaseResult<?> updatePwd(UserVo userVo) {
        String msg = "";
        //得到当前登录的用户
        User user = WebUtils.getCurrentUser();
        String md5 = Md5Utils.md5(userVo.getOldPwd(), user.getSalt(), SYSConstant.PWD_SANLIE);
        if (md5.equals(user.getPwd())) {
            try {
                Integer i = this.userService.changeUserPwd(userVo);
                msg = i > 0 ? SYSConstant.PWD_CHANGE_SUCCESS : SYSConstant.PWD_CHANGE_ERROR;
                return BaseResult.success(ResultCode.valueOf(ResultCode.SUCCESS.getCode()), msg);
            } catch (BaseException e) {
                msg = SYSConstant.PWD_CHANGE_ERROR;
            }
        } else {
            return BaseResult.error(ResultCode.valueOf(ResultCode.FAIL.getCode()), SYSConstant.PWD_OLD_ERROR);
        }
        return BaseResult.error(ResultCode.valueOf(ResultCode.FAIL.getCode()), msg);

    }
}
