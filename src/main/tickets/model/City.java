package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "city")
public class City {

    @Id
    @Column(name = "code")
    private String id;
    @Column(name = "name", unique = true, nullable = false)
    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public City() {

    }

    public City(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String toString() {
        return name;
    }

}
