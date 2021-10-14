package bookstore.publishing;

import bookstore.author.Author;
import bookstore.persistence.PublisherDAO;
import java.util.List;
import java.util.UUID;

public class PublisherService {

    private final PublisherDAO publisherDAO;

    public PublisherService() {
        this.publisherDAO = new PublisherDAO();
    }

    public Publisher createPublisher(String name) {
        Publisher publisher = new Publisher();
        try {
            if (name.equals(searchPublisherforName(name).toString())) {
                publisher.setName(name);
                publisher.setId(UUID.randomUUID().toString());
                publisherDAO.savePublisher(publisher);
                return publisher;
            }
            if (!name.equals(searchPublisherforName(name).toString())) {
                return searchPublisherforName(name);
            }
            return publisher;
        } catch (Exception e) {
            System.out.println("No se guardo la Editorial de forma correcta" + e.getMessage());
            return null;
        }
    }

    public void deletePublisherforName(String name) throws Exception {
        try {
            //Validamos 
            if (name == null || name.trim().isEmpty()) {
                throw new Exception("*** DEBE INDICAR EL NOMBRE DE LA EDITORIAL ***");
            }
            publisherDAO.deletePublisherforName(name);
            if (name != null && !name.trim().isEmpty()) {
                System.out.println("*** EDITORIAL ELIMINADA CON EXITO ***\n");
            }
        } catch (Exception e) {
            throw e;
        }
    }
    
    public void deletePublisherforId(String id) throws Exception {
        try {
            //Validamos 
            if (id == null || id.trim().isEmpty()) {
                throw new Exception("*** DEBE INDICAR EL ID DE LA EDITORIAL ***");
            }
            publisherDAO.deletePublisherforId(id);
            if (id != null && !id.trim().isEmpty()) {
                System.out.println("*** EDITORIAL ELIMINADA CON EXITO ***\n");
            }
        } catch (Exception e) {
            throw e;
        }
    }
    
    public Publisher searchPublisherforName(String name) throws Exception {
        try {

            //Validamos
            if (name == null || name.trim().isEmpty()) {
                throw new Exception("Debe indicar el Nombre de la Editorial");
            }

            Publisher publisher = publisherDAO.searchPublisherName(name);
            return publisher;
        } catch (Exception e) {
            throw e;
        }
    }
    
    public List<Publisher> listPublishers() throws Exception {

        try {
            List<Publisher> publisherS = publisherDAO.listPublisher();
            return publisherS;
        } catch (Exception e) {
            throw e;
        }
    }

    public void printPublishers() throws Exception {
        try {
            //Listamos los libros
            List<Publisher> publishers = listPublishers();

            //Imprimimos los libros - Solo algunos atributos....
            if (publishers.isEmpty()) {
                throw new Exception("No existen libros para imprimir");
            } else {
//                System.out.println("*****************************************");
//                System.out.printf("%-50s%-12s%-15s\n", "ID", "NOMBRE", "ALTA");
                for (Publisher a : publishers) {
                    System.out.println(a);
                }
                System.out.println("");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());;
        }
    }
}
