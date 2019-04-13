package service.tokenActions;

import Utils.PasswordEncoder;
import exeption.exception.AuthenticationException;
import model.Subject.User;
import intf.iUserService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class UserValidator {

    @Inject
    private iUserService userService;

    @Inject
    private PasswordEncoder passwordEncoder;

    public User validateUserByPassword(User user){
        return validateCredentials(user.getUsername(),user.getPassword().getValue());
    }

    public User validateCredentials(String username, String password) {

        User user = userService.findByUsernameOrEmail(username);

        if (user == null) {
            // User cannot be found with the given username/email
            throw new AuthenticationException("Bad credentials.");
        }

        if (!user.isActive()) {
            // User is not active
            throw new AuthenticationException("The user is inactive.");
        }

        if (!passwordEncoder.checkPassword(password, user.getPassword().getValue())) {
            // Invalid password
            throw new AuthenticationException("Bad credentials.");
        }

        return user;
    }
}