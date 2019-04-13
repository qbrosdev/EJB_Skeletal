package intf;

import model.Subject.User;

/**
 * Created by V.Ghasemi
 * on 12/18/2018.
 */
public interface iUserService {
    User findByUsernameOrEmail(String username);
}
