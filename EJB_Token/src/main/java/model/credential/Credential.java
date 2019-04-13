package model.credential;

/**
 * Created by V.Ghasemi
 * on 12/23/2018.
 */
public abstract class Credential<T> {

    public enum Type {
        PASSWORD,TOKEN,CERT
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
