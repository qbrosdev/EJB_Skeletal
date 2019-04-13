package model.Subject;

import model.Principal.Email;
import model.Principal.UserId;
import model.Role;
import model.credential.PassWord;

import java.util.Set;

/**
 * Created by V.Ghasemi
 * on 12/18/2018.
 */
public class User extends Subject {

    private UserId userID;
    private Email email;
    private PassWord password;
    private String firstName;
    private String lastName;
    private String username;
    private boolean active;
    private Set<Role> roleSet;

    public UserId getUserID() {
        return userID;
    }

    public void setUserID(UserId userID) {
        this.userID = userID;
    }

    public Email getEmail() {
        return email;
    }

    public void setEmail(Email email) {
        this.email = email;
    }

    public PassWord getPassword() {
        return password;
    }

    public void setPassword(PassWord password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Set<Role> getRoleSet() {
        return roleSet;
    }

    public void setRoleSet(Set<Role> roleSet) {
        this.roleSet = roleSet;
    }
}
