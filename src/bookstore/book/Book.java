package bookstore.book;

import bookstore.author.Author;
import bookstore.publishing.Publisher;
import com.sun.istack.internal.NotNull;
import java.util.Calendar;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Book {
    @Id
    @GeneratedValue
    private String id;
    
    @NotNull
    @Column(unique = true)
    private Long isbn;
    
    @NotNull // Que no sea nulo
    @Column(unique = true) // Para que no permite duplicar este campo en otros registros.
    private String title;
    
    @Temporal(TemporalType.DATE)
    private Calendar yeaar;
    
    @NotNull
    @Column(unique = true)
    private Integer copies; //ejemplares
    
    @NotNull
    @Column(unique = true)
    private Integer borrowedcopies; //ejemplares prestados
    
    @NotNull
    @Column(unique = true)
    private Integer remainingcopies; //ejemplares restantes
    
    @NotNull
    @Column(unique = true)
    private Boolean toregister; //ejemplares de alta
    
    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(nullable = false)
    private Author author;
    
    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(nullable = false)
    private Publisher publisher;

    public Book() {
        this.toregister = true;
    }

    public Book(Long isbn, String title, Calendar yeaar, Integer copies, Integer borrowedcopies, Integer remainingcopies, Boolean toregister, Author author, Publisher publisher) {
        this.isbn = isbn;
        this.title = title;
        this.yeaar = yeaar;
        this.copies = copies; //ejemplares
        this.borrowedcopies = borrowedcopies; //ejemplares prestados
        this.remainingcopies = remainingcopies; //ejemplares restantes
        this.toregister = toregister; //ejemplares de alta
        this.author = author;
        this.publisher = publisher;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getIsbn() {
        return isbn;
    }

    public void setIsbn(Long isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Calendar getYeaar() {
        return yeaar;
    }

    public void setYeaar(Calendar yeaar) {
        this.yeaar = yeaar;
    }

    public Integer getCopies() {
        return copies;
    }

    public void setCopies(Integer copies) {
        this.copies = copies;
    }

    public Integer getBorrowedcopies() {
        return borrowedcopies;
    }

    public void setBorrowedcopies(Integer borrowedcopies) {
        this.borrowedcopies = borrowedcopies;
    }

    public Integer getRemainingcopies() {
        return remainingcopies;
    }

    public void setRemainingcopies(Integer remainingcopies) {
        this.remainingcopies = remainingcopies;
    }

    public Boolean getToregister() {
        return toregister;
    }

    public void setToregister(Boolean toregister) {
        this.toregister = toregister;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public void setPublisher(Publisher publishing) {
        this.publisher = publishing;
    }

    @Override
    public String toString() {
        return " ID: " + id +
               "\n ISBN: " + isbn +
               "\n Titulo: " + title +
               "\n Fecha de lanzamiento: " + yeaar.get(Calendar.YEAR) +
               "\n Autor: " + author.getName() +
               "\n Editorial: " + publisher.getName();
    }
}
