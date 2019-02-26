package com.cxs.sys.realm;

import com.cxs.sys.constant.SYSConstant;
import com.cxs.sys.domain.User;
import com.cxs.sys.service.PermissionService;
import com.cxs.sys.service.UserService;
import com.cxs.sys.utils.ActiverUser;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Author:chenxiaoshuang
 * @Date:2019/2/20 10:48
 */
@Component
public class MyRealm extends AuthorizingRealm {
    /**
     * 自定义realm认证授权
     */

    @Autowired
    private UserService userService;


    @Autowired
    private PermissionService permissionService;

    @Override
    public String getName() {
        return this.getClass().getSimpleName();
    }


    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String username = token.getPrincipal().toString();
        User user = this.userService.getUserByLoginname(username);
        if (null != user) {
            ActiverUser activerUser = new ActiverUser();
            activerUser.setUser(user);
            //查询角色

            //查询权限
            activerUser.setPermissions(this.permissionService.getUserPermissionStrForList(user.getId()));
            ByteSource source = ByteSource.Util.bytes(user.getSalt());
            AuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(activerUser, user.getPwd(), source, getName());
            return authenticationInfo;
        }

        return null;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        ActiverUser activerUser = (ActiverUser) principals.getPrimaryPrincipal();
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        if (activerUser.getUser().getType().intValue() == SYSConstant.USER_TYPE_SUPER) {
            info.addStringPermission("*:*");
        } else {
            if (null != activerUser.getRoles() && !activerUser.getRoles().isEmpty()) {
                info.addRoles(activerUser.getRoles());
            }
            if (null != activerUser.getPermissions() && !activerUser.getPermissions().isEmpty()) {
                info.addStringPermissions(activerUser.getPermissions());
            }
        }
        return info;
    }

}
