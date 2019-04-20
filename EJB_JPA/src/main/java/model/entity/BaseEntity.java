package model.entity;

import javax.persistence.*;

@MappedSuperclass
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class BaseEntity {

    @Column(name = "id")
    @GeneratedValue
    @Id
    private long id;

    public BaseEntity() { }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
