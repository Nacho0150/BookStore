package bookstore.persistence;

import bookstore.book.Book;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public final class BookDAO {

    private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("13-BookStorePU");
    private final EntityManager em = emf.createEntityManager();
                
    public void saveBook(Book book) throws Exception {
        try {
            em.getTransaction().begin();
            em.persist(book); // Para guardar - envio el objeto completo
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new Exception("*** ERROR AL GUARDAR EL LIBRO ***");
        }
    }

    public void modifyBook(Book book) throws Exception {
        em.getTransaction().begin();
        em.merge(book);
        em.getTransaction().commit();
    }

    public void deleteBookId(String id) throws Exception {
        Book book = searchBookId(id);
        em.getTransaction().begin();
        em.remove(book);
        em.getTransaction().commit();
    }

    public void deleteBookISBN(Long isbn) throws Exception {
        Book book = searchBookIsbn(isbn);
        em.getTransaction().begin();
        em.remove(book);
        em.getTransaction().commit();
    }
    
    public void deleteBookTitle(String title) throws Exception {
        Book book = searchBookTitle(title);
        em.getTransaction().begin();
        em.remove(book);
        em.getTransaction().commit();
    }

    public Book searchBookId(String id) throws Exception {
        Book book = em.find(Book.class, id); // Esto que envio es la llave primaria
        return book;
    }
    //CONSULTA CON PARAMETROS

    public Book searchBookIsbn(Long isbn) throws Exception {
        Book book = (Book) em.createQuery("SELECT b "
                + " FROM Book b"
                + " WHERE b.isbn = :isbn").
                setParameter("isbn", isbn).
                getSingleResult();      
        return book;
    }
    //CONSULTA SIN PARAMETROS
    
    public Book searchBookTitle(String title) throws Exception {
        Book book = (Book) em.createQuery("SELECT b "
                + " FROM Book b"
                + " WHERE Upper(b.title) = :title").
                setParameter("title", title.toUpperCase()).
                getSingleResult();      
        return book;
    }
    
    public List<Book> searchBookAuthorName(String name) throws Exception {
        List<Book> books = em.createQuery("SELECT b"
                + " FROM Book b"
                + " WHERE Upper(b.author.name) LIKE CONCAT('%', :name, '%')", Book.class).
                setParameter("name", name.toUpperCase()).
                getResultList(); 
        return books;
    }
    
    public List<Book> searchBookPublisherName(String name) throws Exception {
        List<Book> books = em.createQuery("SELECT b"
                + " FROM Book b"
                + " WHERE Upper(b.publisher.name) LIKE CONCAT('%', :name, '%')", Book.class).
                setParameter("name", name.toUpperCase()).
                getResultList(); 
        return books;
    }
    //CONSULTA SIN PARAMETROS

    public List<Book> listBooks() throws Exception {
        List<Book> books = em.createQuery("SELECT b FROM Book b")
                .getResultList();
        return books;
    }
}
