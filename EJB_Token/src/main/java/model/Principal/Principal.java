package model.Principal;

/*
Credential
        A Credential is a piece of information that verifies the identity of a user/Subject.
        One (or more) credentials are submitted along with Principal(s) during an authentication attempt to verify
        that the user/Subject submitting them is actually the associated user.
        Credentials are usually very secret things that only a particular user/Subject would know, such as a password.
        The idea is that for a principal, only one person would know the correct credential to ‘pair’ with that principal.
Principal
        A Principal is any identifying attribute of an application user (Subject). An ‘identifying attribute’ can be
        anything that makes sense to your application - a username, a surname, a given name, a user ID, etc.
        Shiro also references something we call a Subject’s primary principal. A Primary principal is any principal
        that uniquely identifies the Subject across the entire application.
        Ideal primary principals are things like a username or a user ID that is a RDBMS user table primary key.

        LINK: https://stackoverflow.com/questions/4989063/what-is-the-meaning-and-difference-between-subject-user-and-principal
*/

public abstract class Principal<T> {

    public enum Type {
       USERNAME,EMAIL,FINGER;
    }

    private Type type;
    private T value;

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }
}