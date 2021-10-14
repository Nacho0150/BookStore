package bookstore.persistence;

import bookstore.author.Author;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;

public class AuthorDAO {

    private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("13-BookStorePU");
    private final EntityManager em = emf.createEntityManager();

    public void saveAuthor(Author author) throws Exception { // Este metodo es para ingresar 
        try {
            em.getTransaction().begin();
            em.persist(author); // Para guardar - envio el objeto completo
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new Exception("*** ERROR AL GUARDAR EL AUTOR ***");
        }
    }

    public void modifyAuthor(Author author) throws Exception { // Este metodo es para ingresar o modificar
        em.getTransaction().begin();
        em.merge(author); // Para modificar un objeto - Sobreescribe los atributos
        em.getTransaction().commit();
    }

    public void deleteAuthorforId(String id) throws Exception {
        Author author = searchIdAuthor(id);
        em.getTransaction().begin(); //Inicio   
        em.remove(author);
        em.getTransaction().commit(); //Fin (Si algo fallo ... vuelve pa atras)    
    }

    public void deleteAuthorforName(String name) throws Exception {
        Author author = searchNameAuthor(name);
        em.getTransaction().begin(); //Inicio   
        em.remove(author);
        em.getTransaction().commit(); //Fin (Si algo fallo ... vuelve pa atras)    
    }

    public Author searchIdAuthor(String id) throws Exception {
        Author author = em.find(Author.class, id); // Para traer objeto con el ID
        return author;
    }

    public Author searchNameAuthor(String name) throws Exception {
        try {
            Author author = (Author) em.createQuery("SELECT a"
                    + " FROM Author a"
                    + " WHERE Upper(a.name) LIKE CONCAT('%', :name, '%')").
                    setParameter("name", name.toUpperCase()).
                    getSingleResult();
            return author;
        } catch (NoResultException e) {
            return null;
        } catch (Exception e) {
            throw new Exception("Erro al traer el nombre del autor");
        }
    }

    //CONSULTA SIN PARAMETROS
    public List<Author> listAuthors() throws Exception {
        List<Author> authors = em.createQuery("SELECT a FROM Author a")
                .getResultList();
        return authors;
    }
}
