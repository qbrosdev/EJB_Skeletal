package com.qbros.Shiro.Realm;

import intf.iTokenProvider;
import model.Role;
import model.Subject.User;
import model.credential.Token.ShiroLoginToken;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAccount;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.ops4j.pax.shiro.cdi.ShiroIni;

import javax.inject.Inject;
import java.util.HashSet;
import java.util.Set;

@ShiroIni
public class JWTRealm extends AuthorizingRealm {

    @Inject
    private iTokenProvider tokenProvider;

    @Override
    public boolean supports(AuthenticationToken token) {
        return token != null && token instanceof ShiroLoginToken;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) {
        ShiroLoginToken loginToken = (ShiroLoginToken) token;
        User user = tokenProvider.findById(null);

        if (user != null && tokenProvider.validateToken(null)) {
            SimpleAccount account = new SimpleAccount(user,"token" , getName());
            account.addRole(convertToStringRoleCollection(user.getRoleSet()));
            return account;
        }
        return null;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        return new SimpleAuthorizationInfo(convertToStringRoleCollection(((User) principals.getPrimaryPrincipal()).getRoleSet()));
    }

    private Set<String> convertToStringRoleCollection(Set<Role> roleSet){
        Set<String> result = new HashSet<>();
        for(Role role: roleSet){
            result.add(role.name());
        }
        return result;
    }

}
