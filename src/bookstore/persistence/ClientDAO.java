package bookstore.persistence;

import bookstore.client.Client;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;

public class ClientDAO {
    
    private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("13-BookStorePU");
    private final EntityManager em = emf.createEntityManager();

    public void saveClient(Client client) throws Exception { // Este metodo es para ingresar 
        try {
            em.getTransaction().begin();
            em.persist(client); // Para guardar - envio el objeto completo
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new Exception("*** ERROR AL GUARDAR EL CLIENTE ***");
        }
    }
//

    public void modifyAuthor(Client client) throws Exception { // Este metodo es para ingresar o modificar
        em.getTransaction().begin();
        em.merge(client); // Para modificar un objeto - Sobreescribe los atributos
        em.getTransaction().commit();
    }

    public void deleteClientforId(String id) throws Exception {
        Client client = searchforIdClient(id);
        em.getTransaction().begin(); //Inicio   
        em.remove(client);
        em.getTransaction().commit(); //Fin (Si algo fallo ... vuelve pa atras)    
    }

    public void deleteClientforName(String name) throws Exception {
        Client client = searchforNameClient(name);
        em.getTransaction().begin(); //Inicio   
        em.remove(client);
        em.getTransaction().commit(); //Fin (Si algo fallo ... vuelve pa atras)    
    }
    
    public void deleteClientforSurname(String surname) throws Exception {
        Client client = searchforSurnameClient(surname);
        em.getTransaction().begin(); //Inicio   
        em.remove(client);
        em.getTransaction().commit(); //Fin (Si algo fallo ... vuelve pa atras)    
    }

    public Client searchforIdClient(String id) throws Exception {
        Client client = em.find(Client.class, id); // Para traer objeto con el ID
        return client;
    }

    public Client searchforNameClient(String name) throws Exception {
        try {
            Client client = (Client) em.createQuery("SELECT c"
                    + " FROM Client c"
                    + " WHERE Upper(c.name) LIKE CONCAT('%', :name, '%')").
                    setParameter("name", name.toUpperCase()).
                    getSingleResult();
            return client;
        } catch (NoResultException e) {
            return null;
        } catch (Exception e) {
            throw new Exception("*** ERROR AL TRAER EL CLIENTE ***");
        }
    }
    
    public Client searchforSurnameClient(String surname) throws Exception {
        try {
            Client client = (Client) em.createQuery("SELECT c"
                    + " FROM Client c"
                    + " WHERE Upper(c.surname) LIKE CONCAT('%', :surname, '%')").
                    setParameter("surname", surname.toUpperCase()).
                    getSingleResult();
            return client;
        } catch (NoResultException e) {
            return null;
        } catch (Exception e) {
            throw new Exception("*** ERROR AL TRAER EL CLIENTE ***");
        }
    }
    
    public Client searchforPhonenumberClient(String phonenumber) throws Exception {
        try {
            Client client = (Client) em.createQuery("SELECT c"
                    + " FROM Client c"
                    + " WHERE Upper(c.phonenumber) LIKE CONCAT('%', :phonenumber, '%')").
                    setParameter("phonenumber", phonenumber.toUpperCase()).
                    getSingleResult();
            return client;
        } catch (NoResultException e) {
            return null;
        } catch (Exception e) {
            throw new Exception("*** ERROR AL TRAER EL CLIENTE ***");
        }
    }

    //CONSULTA SIN PARAMETROS
    public List<Client> listClients() throws Exception {
        List<Client> clients = em.createQuery("SELECT c FROM Client c")
                .getResultList();
        return clients;
    }
}
