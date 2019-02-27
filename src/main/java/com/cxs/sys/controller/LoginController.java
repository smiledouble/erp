package com.cxs.sys.controller;

import com.cxs.sys.constant.BaseException;
import com.cxs.sys.constant.BaseResult;
import com.cxs.sys.constant.ResultCode;
import com.cxs.sys.constant.SYSConstant;
import com.cxs.sys.service.LogService;
import com.cxs.sys.utils.ActiverUser;
import com.cxs.sys.utils.WebUtils;
import com.cxs.sys.vo.LogVo;
import com.cxs.sys.vo.UserVo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

/**
 * @Author:chenxiaoshuang
 * @Date:2019/2/20 11:08
 */
@Controller
public class LoginController {

    private static final String MODEL = "login";

    @Autowired
    private LogService logService;

    @PostMapping(MODEL + "/login")
    @ResponseBody
    public BaseResult<?> login(@RequestBody UserVo userVo) {
        BaseResult<?> baseResult = null;
        if (null == userVo) {
            throw new BaseException(ResultCode.FAIL);
        }
        // 1,从当前线程里面得到Subject
        Subject subject = SecurityUtils.getSubject();
        // 2,构造用户名和密码
        AuthenticationToken token = new UsernamePasswordToken(userVo.getLoginname(), userVo.getPwd());
        try {
            subject.login(token);
            ActiverUser user = (ActiverUser) subject.getPrincipal();
            if (null != user) {
                WebUtils.getCurrentSession().setAttribute("user", user.getUser());
                //登录成功插入日志
                LogVo logVo = new LogVo();
                logVo.setLogintime(new Date());
                logVo.setLoginip(WebUtils.getCurrentHttpServletRequest().getRemoteAddr());
                logVo.setLoginname(user.getUser().getLoginname() + "-" + user.getUser().getName());
                this.logService.addLog(logVo);
                baseResult = BaseResult.success("/toIndex");
            }
        } catch (IncorrectCredentialsException e) {
            baseResult = BaseResult.error(ResultCode.valueOf(ResultCode.FAIL.getCode()), SYSConstant.LOGIN_ERROR_PWD);
        } catch (UnknownAccountException e) {
            baseResult = BaseResult.error(ResultCode.valueOf(ResultCode.FAIL.getCode()), SYSConstant.LOGIN_ERROR_NAME);
        }
        return baseResult;
    }


}
