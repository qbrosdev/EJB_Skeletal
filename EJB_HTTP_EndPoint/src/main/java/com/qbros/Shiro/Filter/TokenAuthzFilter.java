package com.qbros.Shiro.Filter;

import io.jsonwebtoken.Jwts;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.web.filter.AccessControlFilter;
import service.AuthenticationResourceMethod;

import javax.inject.Inject;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.HttpHeaders;
import javax.xml.bind.DatatypeConverter;

/**
 * Created by QBros on Zero Hour ... Hooah!
 */
public class TokenAuthzFilter extends AccessControlFilter {

    @Inject
    AuthenticationResourceMethod authenticationResourceMethod;

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse arg1, Object arg2) throws Exception {
        boolean accessAllowed = false;
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String jwt = httpRequest.getHeader(HttpHeaders.AUTHORIZATION);
        if (jwt == null || !jwt.startsWith("Bearer ")) {
            return accessAllowed;
        }
        jwt = jwt.substring(jwt.indexOf(" "));
        String username = Jwts.parser().setSigningKey(DatatypeConverter.parseBase64Binary("secret"))
                .parseClaimsJws(jwt).getBody().getSubject();
        String subjectName = (String) SecurityUtils.getSubject().getPrincipal();
        if (username.equals(subjectName)) {
            accessAllowed = true;
        }
        return accessAllowed;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest arg0, ServletResponse arg1) throws Exception {
        HttpServletResponse response = (HttpServletResponse) arg1;
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        return false;
    }

}
