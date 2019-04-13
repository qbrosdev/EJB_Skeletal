package model.credential.Token;

import model.Principal.UserId;
import org.apache.shiro.authc.AuthenticationToken;

public class ShiroToken extends AbsToken implements AuthenticationToken {

    private UserId userId;
    private CustomJwtTokenKeeper customJwtTokenKeeper;

    public ShiroToken(CustomJwtTokenKeeper customJwtTokenKeeper, UserId userId) {
        this.customJwtTokenKeeper = customJwtTokenKeeper;
        this.userId = userId;
    }

    public CustomJwtTokenKeeper getCustomJwtTokenKeeper() {
        return customJwtTokenKeeper;
    }

    public void setCustomJwtTokenKeeper(CustomJwtTokenKeeper customJwtTokenKeeper) {
        this.customJwtTokenKeeper = customJwtTokenKeeper;
    }

    public UserId getUserId() {
        return userId;
    }

    public void setUserId(UserId userId) {
        this.userId = userId;
    }

    @Override
    public Object getPrincipal() {
        return null;
    }

    @Override
    public Object getCredentials() {
        return customJwtTokenKeeper.getValue();
    }
}
