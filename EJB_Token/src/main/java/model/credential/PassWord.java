package model.credential;

/**
 * API model for the authentication token.
 *
 * @author cassiomolin
 */
public  class PassWord extends Credential<String> {

    public PassWord() {
        setType(Type.PASSWORD);
    }

}