package bookstore.loan;

import bookstore.book.Book;
import bookstore.client.Client;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Loan {
    @Id
    @GeneratedValue
    private String id;
    
    @Temporal(TemporalType.DATE)
    private LocalDate loandate; //fecha de prestamo
    
    @Temporal(TemporalType.DATE)
    private LocalDate returndate; //fecha de devolucion
    
    @OneToMany(cascade = CascadeType.REMOVE)
    @JoinColumn(nullable = false)
    private List<Book> books;
    
    @OneToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(nullable = false)
    private Client client;
    
    public Loan() {
    }

    public Loan(LocalDate loandate, LocalDate returndate, List<Book> books, Client client) {
        this.loandate = loandate;
        this.returndate = returndate;
        this.books = books;
        this.client = client;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDate getLoanDate() {
        return loandate;
    }

    public void setLoanDate(LocalDate loandate) {
        this.loandate = loandate;
    }

    public LocalDate getReturnDate() {
        return returndate;
    }

    public void setReturnDate(LocalDate returndate) {
        this.returndate = returndate;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    @Override
    public String toString() {
        return " ID: " + id +
               "\n Fecha de creacion del Prestamo: " + loandate +
               "\n Fecha de devolucion del Prestamo:: " + returndate +
               "\n Libro/s: " + books.toString()  +
               "\n Cliente: " + client.toString();
    }
}