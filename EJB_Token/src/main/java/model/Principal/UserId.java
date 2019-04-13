package model.Principal;

/**
 * Created by V.Ghasemi
 * on 12/23/2018.
 */
public class UserId extends Principal<String> {

    public UserId() {
        setType(Type.USERNAME);
    }
}
