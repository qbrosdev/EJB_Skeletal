package model.entity.inheritance;

import javax.persistence.Entity;
import javax.persistence.Id;


@Entity
public abstract class AbsBaseEntity {

    //https://stackoverflow.com/questions/4381290/hibernate-exception-org-hibernate-annotationexception-no-identifier-specified
    @Id
    private long id;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    private String commonField;

    public String getCommonField() {
        return commonField;
    }

    public void setCommonField(String commonField) {
        this.commonField = commonField;
    }
}
