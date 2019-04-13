package intf;


import model.Principal.UserId;
import model.Subject.User;
import model.credential.Token.AbsToken;
import model.Principal.Principal;
import model.credential.Token.CustomJwtTokenKeeper;

/**
 * Created by QBros on Zero Hour ... Hooah!
 */
public interface iTokenProvider {
    AbsToken generateTokenForUser(User user);
    User findById(UserId userId);
    boolean validateToken(AbsToken token);
}
