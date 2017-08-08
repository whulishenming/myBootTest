package com.lsm.boot.shiro.shiro.realm;

import com.lsm.boot.shiro.model.Role;
import com.lsm.boot.shiro.model.User;
import com.lsm.boot.shiro.model.UserRole;
import com.lsm.boot.shiro.repository.RoleRepository;
import com.lsm.boot.shiro.repository.UserRepository;
import com.lsm.boot.shiro.repository.UserRoleRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

@Slf4j
public class ShiroRealm extends AuthorizingRealm {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserRoleRepository userRoleRepository;
    @Autowired
    private RoleRepository roleRepository;

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authToken) throws AuthenticationException {

        UsernamePasswordToken token = (UsernamePasswordToken) authToken;
        String userName = token.getUsername();
        String password = String.valueOf(token.getPassword());
        //密码进行加密处理
        String passwordEncrypt = Base64.getEncoder().encodeToString(password.getBytes());

        User user = userRepository.findFirstByUserNameAndPassword(userName, passwordEncrypt);
        if (user == null) {

            throw new AccountException("帐号或密码不正确！");
        }else if(user.getStatus()==0){

            throw new DisabledAccountException("帐号已经禁止登录！");
        }

        userRepository.updateLastLoginTimeById(new Date(), user.getId());

        log.info("身份认证成功，登录用户：{}", userName);

        return new SimpleAuthenticationInfo(user, user.getPassword(), getName());
    }

    /**
     * 授权
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {

        User user = (User) SecurityUtils.getSubject().getPrincipal();
        SimpleAuthorizationInfo info =  new SimpleAuthorizationInfo();
        //根据用户ID查询角色（role），放入到Authorization里。
        Set<String> roleSet = new HashSet<>();
        List<UserRole> userRoleList = userRoleRepository.findByUserIdAndIsDeleted(user.getId(), (byte) 0);
        List<Long> roleIds = new ArrayList<>();
        for (UserRole userRole : userRoleList) {
            roleIds.add(userRole.getId());
        }
        List<Role> roles = roleRepository.findByIds(roleIds);
        for (Role role : roles) {
            roleSet.add(role.getRoleType());
        }

        //根据用户ID查询权限（permission），放入到Authorization里。
		/*List<SysPermission> permissionList = sysPermissionService.selectByMap(map);
		Set<String> permissionSet = new HashSet<String>();
		for(SysPermission Permission : permissionList){
			permissionSet.add(Permission.getName());
		}*/
        Set<String> permissionSet = new HashSet<String>();
        permissionSet.add("权限添加");
        permissionSet.add("权限删除");
        info.setStringPermissions(permissionSet);
        return info;
    }

}
