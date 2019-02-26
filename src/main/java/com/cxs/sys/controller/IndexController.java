package com.cxs.sys.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @Author:chenxiaoshuang
 * @Date:2019/2/20 15:04
 */
@Controller
public class IndexController {

    private static final String MODEL = "index";

    /**
     * 跳转到主页
     *
     * @return
     */
    @GetMapping("toIndex")
    public ModelAndView toIndex() {
        ModelAndView view = new ModelAndView("index");
        return view;
    }

    /**
     * 跳转到工作台
     *
     * @return
     */
    @GetMapping(MODEL + "/toMain")
    public ModelAndView toMain() {
        ModelAndView view = new ModelAndView("main");
        return view;
    }

    /**
     * 跳转到日志管理
     *
     * @return
     */
    @GetMapping(MODEL + "/toLogInfoManager")
    public ModelAndView toLogInfoManager() {
        ModelAndView view = new ModelAndView("system/logInfo/logInfoManager");
        return view;
    }

    /**
     * 跳转到系统公告
     *
     * @return
     */
    @GetMapping(MODEL + "/toNoticeManager")
    public ModelAndView toNoticeManager() {
        ModelAndView view = new ModelAndView("system/notice/noticeManager");
        return view;
    }

    /**
     * 跳转到图标管理
     *
     * @return
     */
    @GetMapping(MODEL + "/toIconManager")
    public ModelAndView toIconManager() {
        ModelAndView view = new ModelAndView("system/icon/iconManager");
        return view;
    }

    /**
     * 跳转到部门管理页面
     *
     * @return
     */
    @GetMapping(MODEL + "/toDeptManager")
    public ModelAndView toDeptManager() {
        ModelAndView view = new ModelAndView("system/dept/deptManager");
        return view;
    }

    /**
     * 跳转到部门左边的树
     *
     * @return
     */
    @GetMapping(MODEL + "/toDeptLeftManager")
    public ModelAndView toDeptLeftManager() {
        ModelAndView view = new ModelAndView("system/dept/deptLeftManager");
        return view;
    }

    /**
     * 跳转到部门右边的表格
     *
     * @return
     */
    @GetMapping(MODEL + "/toDeptRightManager")
    public ModelAndView toDeptRightManager() {
        ModelAndView view = new ModelAndView("system/dept/deptRightManager");
        return view;
    }


    /**
     * 跳转到菜单管理
     *
     * @return
     */
    @GetMapping(MODEL + "/toMenuManager")
    public ModelAndView toMenuManager() {
        ModelAndView view = new ModelAndView("system/menu/menuManager");
        return view;
    }

    /**
     * 跳转到菜单左边的树管理
     *
     * @return
     */
    @GetMapping(MODEL + "/toMenuLeftManager")
    public ModelAndView toMenuLeftManager() {
        ModelAndView view = new ModelAndView("system/menu/menuLeftManager");
        return view;
    }

    @GetMapping(MODEL + "/toMenuRightManager")
    public ModelAndView toMenuRightManager() {
        ModelAndView view = new ModelAndView("system/menu/menuRightManager");
        return view;
    }

    /**
     * 跳转到权限管理
     *
     * @return
     */
    @GetMapping(MODEL + "/toPermissionManager")
    public ModelAndView toPermissionManager() {
        ModelAndView view = new ModelAndView("system/permission/permissionManager");
        return view;
    }

    /**
     * 跳转到权限左边的树管理
     *
     * @return
     */
    @GetMapping(MODEL + "/toPermissionLeftManager")
    public ModelAndView toPermissionLeftManager() {
        ModelAndView view = new ModelAndView("system/permission/permissionLeftManager");
        return view;
    }

    /**
     * 跳转到权限右边的表格管理
     *
     * @return
     */
    @GetMapping(MODEL + "/toPermissionRightManager")
    public ModelAndView toPermissionRightManager() {
        ModelAndView view = new ModelAndView("system/permission/permissionRightManager");
        return view;
    }

    /**
     * 跳转到角色管理
     *
     * @return
     */
    @GetMapping(MODEL + "/toRoleManager")
    public ModelAndView toRoleManager() {
        ModelAndView view = new ModelAndView("system/role/roleManager");
        return view;
    }


    /**
     * 跳转到用户管理
     *
     * @return
     */
    @GetMapping(MODEL + "/toUserManager")
    public ModelAndView toUserManager() {
        ModelAndView view = new ModelAndView("system/user/userManager");
        return view;
    }

    /**
     * 跳转到用户左边的树管理
     *
     * @return
     */
    @GetMapping(MODEL + "/toUserLeftManager")
    public ModelAndView toUserLeftManager() {
        ModelAndView view = new ModelAndView("system/user/userLeftManager");
        return view;
    }

    /**
     * t跳转到用户右边的表格
     *
     * @return
     */
    @GetMapping(MODEL + "/toUserRightManager")
    public ModelAndView toUserRightManager() {
        ModelAndView view = new ModelAndView("system/user/userRightManager");
        return view;
    }


    /**
     * 跳转到需改密码
      * @return
     */
    @GetMapping(MODEL+"/toChangePwd")
    public ModelAndView toChangePwd(){
        ModelAndView view = new ModelAndView("system/user/changePwd");
        return view;
    }

    /**
     * 跳转到个人资料
     * @return
     */
    @GetMapping(MODEL+"/toPersionalInfo")
    public ModelAndView toPersionalInfo(){
        ModelAndView view = new ModelAndView("system/user/persionalInfo");
        return view;
    }

}
