package model.entity.inheritance;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table (name = "AbsChild")
public class AbsChild extends AbsBaseEntity {

    public AbsChild() {}

    private String localValue;

    public String getLocalValue() {
        return localValue;
    }

    public void setLocalValue(String localValue) {
        this.localValue = localValue;
    }
}
