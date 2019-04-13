package model.credential.Token;

/**
 * Created by V.Ghasemi
 * on 12/23/2018.
 */
public class CustomJwtTokenKeeper extends AbsToken {

    public CustomJwtTokenKeeper(String tokenValue) {
        this.setValue(tokenValue);
    }
}
