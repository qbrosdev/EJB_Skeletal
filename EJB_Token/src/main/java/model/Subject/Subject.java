package model.Subject;

import model.Principal.Principal;
import model.credential.Credential;
import java.util.HashMap;
import java.util.Map;

/**
 * info: find implementations of an interface (CTRL + ALT + B)
 */

public class Subject {

    private Map<Principal.Type,Principal> principals = new HashMap<>();
    private Map<Credential.Type, Credential> credentials = new HashMap<>();

    public Principal getPrincipleOfType(Principal.Type type) {
        return principals.get(type);
    }

    public Credential getCredentialOfType(Credential.Type type) {
        return credentials.get(type);
    }

    public void setPrincipalOfType(Principal.Type type, Principal principal){
        principals.put(type,principal);
    }

}
