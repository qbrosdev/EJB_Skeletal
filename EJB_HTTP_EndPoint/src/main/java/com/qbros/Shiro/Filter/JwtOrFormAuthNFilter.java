package com.qbros.Shiro.Filter;

import Utils.JsonMapper;
import intf.iTokenProvider;
import model.credential.Token.ShiroLoginToken;
import model.credential.Token.ShiroToken;
import org.apache.commons.io.IOUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.web.filter.authc.AuthenticatingFilter;
import org.apache.shiro.web.util.WebUtils;

import javax.inject.Inject;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.HttpHeaders;
import java.io.IOException;

/*
https://github.com/panchitoboy/shiro-jwt/blob/master/src/main/java/com/github/panchitoboy/shiro/jwt/filter/JWTOrFormAuthenticationFilter.java
we extend authenticating filter instead of authenticationFilter
(Base of all filters: https://shiro.apache.org/static/1.3.1/apidocs/org/apache/shiro/web/filter/authc/AuthenticationFilter.html)
*/

public class JwtOrFormAuthNFilter extends AuthenticatingFilter {

    @Inject
    iTokenProvider tokenProvider;

    public JwtOrFormAuthNFilter() {
        setLoginUrl(DEFAULT_LOGIN_URL);
    }

    @Override
    public void setLoginUrl(String loginUrl) {
        String previous = getLoginUrl();
        if (previous != null) {
            this.appliedPaths.remove(previous);
        }
        super.setLoginUrl(loginUrl);
        this.appliedPaths.put(getLoginUrl(), null);
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {

        boolean loggedIn = false;

        if (isLoginRequest(request, response) || hasAuthHeader(request)) {
            loggedIn = executeLogin(request, response);
        }

        if (!loggedIn) {
            HttpServletResponse httpResponse = WebUtils.toHttp(response);
            httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        }

        return loggedIn;
    }

    /**
     * info:  To auto generate javadoc for a method: type (/**) and press ENTER
     * /**
     * this method is called within the execute login method. Execute login takes the servlet req and resp as
     * input and generates a login token
     *
     * @param request  ss
     * @param response vv
     * @return AuthenticationToken
     * @throws IOException s
     */
    @Override
    protected AuthenticationToken createToken(ServletRequest request, ServletResponse response) throws IOException {

        /*has previously logged in and has a JWT token*/
        if (hasAuthHeader(request)) {
            String headerContent = getAuthzHeader(request);
            if (headerContent != null) {
                return extractJwtToken(headerContent);
            } else {
                return generateDummyToken();
            }
        }

        /*is trying to get a token by, passing credentials */
        if (isLoginRequest(request, response)) {
            return generateLoginToken(request);
        }

        /*no Match found */
        return generateDummyToken();
    }

    private AuthenticationToken generateDummyToken() {
        return new UsernamePasswordToken();
    }

    @Override
    protected boolean onLoginFailure(AuthenticationToken token, AuthenticationException e, ServletRequest request, ServletResponse response) {
        HttpServletResponse httpResponse = WebUtils.toHttp(response);
        httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        return false;
    }

    private AuthenticationToken generateLoginToken(ServletRequest request) throws IOException {
        String jsonString = IOUtils.toString(request.getInputStream());
        if(jsonString != null && !jsonString.isEmpty()){
            return JsonMapper.JsonStringToObj(jsonString, ShiroLoginToken.class);
        }else {
            return generateDummyToken();
        }
    }

    private String getAuthzHeader(ServletRequest request) {
        HttpServletRequest httpRequest = WebUtils.toHttp(request);
        return httpRequest.getHeader(HttpHeaders.AUTHORIZATION);
    }

    private boolean hasAuthHeader(ServletRequest request) {
        String authzHeader = getAuthzHeader(request);
        return authzHeader != null;
    }

    private ShiroToken extractJwtToken(String token) {
        return null;
    }
}