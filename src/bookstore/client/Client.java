package bookstore.client;

import com.sun.istack.internal.NotNull;
import java.util.Calendar;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Client {
    @Id
    @GeneratedValue
    private String id;
    
    @NotNull
    @Column(unique = true)
    private Long document;
    
    @NotNull
    @Column(unique = true)
    private String name;
    
    @NotNull
    @Column(unique = true)
    private String surname;
    
    @NotNull
    @Column(unique = true)
    private String phonenumber;

    public Client() {
    }

    public Client(Long document, String name, String surname, String phonenumber) {
        this.document = document;
        this.name = name;
        this.surname = surname;
        this.phonenumber = phonenumber;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getDocument() {
        return document;
    }

    public void setDocument(Long document) {
        this.document = document;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    @Override
    public String toString() {
        return " ID: " + id +
               "\n Nombre: " + name +
               "\n Apellido: " + surname +
               "\n DNI: " + document +
               "\n Telefono: " + phonenumber;
    }
}
