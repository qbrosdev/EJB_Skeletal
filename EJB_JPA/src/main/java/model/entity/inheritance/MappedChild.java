package model.entity.inheritance;

import javax.persistence.Entity;


@Entity
public class MappedChild extends MappedBaseEntity {

    public MappedChild() {}

    private String localValue;

    public String getLocalValue() {
        return localValue;
    }

    public void setLocalValue(String localValue) {
        this.localValue = localValue;
    }
}
