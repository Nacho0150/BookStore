package bookstore.author;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Author {
    @Id
    @GeneratedValue
    private String id;
    
    @Column(nullable = false)
    private String name;
    
    private Boolean toregister; //ejemplares de alta

    public Author() {
        this.toregister = true;
    }

    public Author(String name, Boolean toregister) {
        this.name = name;
        this.toregister = toregister;
    }

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

    public Boolean getToregister() {
        return toregister;
    }

    public void setToregister(Boolean toregister) {
        this.toregister = toregister;
    }

    @Override
    public String toString() {
        return "ID: " + id +
               "\nAutor: " + name +
               "\nAlta: " + toregister;
    }
}
