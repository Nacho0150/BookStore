package bookstore.persistence;

import bookstore.publishing.Publisher;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;

public class PublisherDAO {

    private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("13-BookStorePU");
    private final EntityManager em = emf.createEntityManager();

    public void savePublisher(Publisher publisher) throws Exception { // Este metodo es para ingresar 
        try {
            em.getTransaction().begin();
            em.persist(publisher); // Para guardar - envio el objeto completo
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new Exception("*** ERROR AL GUARDAR LA EDITORIAL ***");
        }
    }

    public void modifyPublisher(Publisher publisher) throws Exception { // Este metodo es para ingresar o modificar
        em.getTransaction().begin();
        em.merge(publisher); // Para modificar un objeto - Sobreescribe los atributos
        em.getTransaction().commit();
    }

    public void deletePublisherforId(String id) throws Exception {
        Publisher publisher = searchPublisherId(id);
        em.getTransaction().begin(); //Inicio   
        em.remove(publisher);
        em.getTransaction().commit(); //Fin (Si algo fallo ... vuelve pa atras)    
    }

    public void deletePublisherforName(String name) throws Exception {
        Publisher publisher = searchPublisherName(name);
        em.getTransaction().begin(); //Inicio   
        em.remove(publisher);
        em.getTransaction().commit(); //Fin (Si algo fallo ... vuelve pa atras)    
    }
    
    public Publisher searchPublisherId(String id) throws Exception {
        Publisher publisher = em.find(Publisher.class, id); // Para traer objeto con el ID
        return publisher;
    }

    public Publisher searchPublisherName(String name) throws Exception {
        try {
            Publisher publisher = (Publisher) em.createQuery("SELECT p"
                    + " FROM Publisher p"
                    + " WHERE Upper(p.name) LIKE CONCAT('%', :name, '%')").
                    setParameter("name", name.toUpperCase()).
                    getSingleResult();
            return publisher;
        } catch (NoResultException e) {
            return null;
        } catch (Exception e) {
            throw new Exception("*** ERROR AL TRAER EL NOMBRE DE LA EDITORIAL ***");
        }
    }

    //CONSULTA SIN PARAMETROS
    public List<Publisher> listPublisher() throws Exception {
        List<Publisher> publishers = em.createQuery("SELECT p FROM Publisher p")
                .getResultList();
        return publishers;
    }
}
