package bookstore.persistence;

import bookstore.loan.Loan;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class LoanDAO {
    
    private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("13-BookStorePU");
    private final EntityManager em = emf.createEntityManager();
                
    public void saveLoan(Loan loan) throws Exception {
        try {
            em.getTransaction().begin();
            em.persist(loan); // Para guardar - envio el objeto completo
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new Exception("*** ERROR AL GUARDAR EL PRESTAMO ***");
        }
    }

    public void modifyLoan(Loan loan) throws Exception {
        em.getTransaction().begin();
        em.merge(loan);
        em.getTransaction().commit();
    }

    public void deleteLoanforId(String id) throws Exception {
        Loan loan = searchLoanId(id);
        em.getTransaction().begin();
        em.remove(loan);
        em.getTransaction().commit();
    }

    public Loan searchLoanId(String id) throws Exception {
        Loan loan = em.find(Loan.class, id); // Esto que envio es la llave primaria
        return loan;
    }
    //CONSULTA CON PARAMETROS

//    public Book searchBookIsbn(Long isbn) throws Exception {
//        Book book = (Book) em.createQuery("SELECT b "
//                + " FROM Book b"
//                + " WHERE b.isbn = :isbn").
//                setParameter("isbn", isbn).
//                getSingleResult();      
//        return book;
//    }
    //CONSULTA SIN PARAMETROS
    
//    public Book searchBookTitle(String title) throws Exception {
//        Book book = (Book) em.createQuery("SELECT b "
//                + " FROM Book b"
//                + " WHERE Upper(b.title) = :title").
//                setParameter("title", title.toUpperCase()).
//                getSingleResult();      
//        return book;
//    }
//    
    public List<Loan> searchLoanforClientName(String name) throws Exception {
        List<Loan> loans = em.createQuery("SELECT l"
                + " FROM Loan l"
                + " WHERE Upper(l.client.name) LIKE CONCAT('%', :name, '%')", Loan.class).
                setParameter("name", name.toUpperCase()).
                getResultList(); 
        return loans;
    }
//    
//    public List<Book> searchBookPublisherName(String name) throws Exception {
//        List<Book> books = em.createQuery("SELECT b"
//                + " FROM Book b"
//                + " WHERE Upper(b.publisher.name) LIKE CONCAT('%', :name, '%')", Book.class).
//                setParameter("name", name.toUpperCase()).
//                getResultList(); 
//        return books;
//    }
    //CONSULTA SIN PARAMETROS

    public List<Loan> listLoans() throws Exception {
        List<Loan> loans = em.createQuery("SELECT l FROM Loan l")
                .getResultList();
        return loans;
    }
}
