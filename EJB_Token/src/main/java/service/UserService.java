package service;

import intf.iUserService;
import model.Subject.User;

import javax.enterprise.context.ApplicationScoped;

/**
 * Created by QBros on Zero Hour ... Hooah!
 */
@ApplicationScoped
public class UserService implements iUserService {
    @Override
    public User findByUsernameOrEmail(String username) {
        return new User();
    }
}
