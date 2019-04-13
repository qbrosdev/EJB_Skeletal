package model.credential.Token;

import model.credential.Credential;

public abstract class AbsToken extends Credential<String> {

    /*
    when we define a class to be an Abstract Class it cannot be instantiated but that does not mean an
    Abstract class cannot have a constructor. Each abstract class must have a concrete subclass which
    will implement the abstract methods of that abstract class. ... Hence we can have a constructor in
    abstract classes.
     */

    public AbsToken() {
        setType(Type.TOKEN);
    }
}