package com.flycinema.flycinema_boot.shiro.realm;

import com.flycinema.flycinema_boot.entity.UserDO;
import com.flycinema.flycinema_boot.entity.UserPermsDO;
import com.flycinema.flycinema_boot.service.UserService;
import com.flycinema.flycinema_boot.shiro.MyByteSource;
import com.flycinema.flycinema_boot.shiro.MySimpleByteSource;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

/**
 * @ClassName CustomerRealm
 * @Author admin
 * @Date 2021/3/29 11:19
 */
public class CustomerRealm extends AuthorizingRealm {
    @Autowired
    private UserService userService;
    /**
     * 授权
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //获取身份信息
        Subject subject = SecurityUtils.getSubject();
        UserDO userDO = (UserDO) subject.getPrincipal();
        System.out.println("权限认证"+userDO);
//        String principal = (String) principalCollection.getPrimaryPrincipal();
        //根据身份信息获取角色和权限
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        //数据库查询当前用户的授权字符串
        UserPermsDO permsByName = userService.findPermsByName(userDO.getUsername());
        simpleAuthorizationInfo.addStringPermission(permsByName.getPerms());
        return simpleAuthorizationInfo;
    }

    /**
     * 认证
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String pri =(String) token.getPrincipal();
        UserDO user = userService.findUser(pri);
        if(!StringUtils.isEmpty(user)){
            return  new SimpleAuthenticationInfo(user,user.getPassword(),
                    new MySimpleByteSource(user.getPayment()),this.getName());
        }
        return null;
    }
}
