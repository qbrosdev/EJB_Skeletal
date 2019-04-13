package service;

import intf.iTokenProvider;
import model.Principal.UserId;
import model.credential.Token.AbsToken;
import model.credential.Token.CustomJwtTokenKeeper;
import model.Subject.User;
import model.credential.Token.ShiroToken;
import service.tokenActions.AuthenticationTokenService;
import service.tokenActions.UserValidator;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class AuthenticationResourceMethod implements iTokenProvider {

    @Inject
    private UserValidator userValidator;

    @Inject
    private AuthenticationTokenService authenticationTokenService;

    @Override
    public AbsToken generateTokenForUser(User user) {
        User result = userValidator.validateUserByPassword(user);
        String token = authenticationTokenService.issueToken(user.getUsername(), result.getRoleSet());
        AbsToken authenticationToken = new ShiroToken(new CustomJwtTokenKeeper(token),result.getUserID());
        return authenticationToken;
    }

    @Override
    public User findById(UserId userId) {
        return new User();
    }

    @Override
    public boolean validateToken(AbsToken token) {
        return true;
    }

//    public Response refresh() {
//        AuthenticationTokenDetails tokenDetails = ((TokenBasedSecurityContext) securityContext).getAuthenticationTokenDetails();
//        String token = authenticationTokenService.refreshToken(tokenDetails);
//        AbsToken authenticationToken = new CustomJwtTokenKeeper();
//        authenticationToken.setToken(token);
//        return Response.ok(authenticationToken).build();
//    }
}
