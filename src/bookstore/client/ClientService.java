package bookstore.client;

import bookstore.author.Author;
import bookstore.persistence.ClientDAO;
import java.util.List;
import java.util.UUID;

public class ClientService {
    
    private ClientDAO clientDAO;

    public ClientService() {
        this.clientDAO = new ClientDAO();
    }
    
    public Client createClient(Long document, String name, String surname, String phonenumber) throws Exception {
        Client client = new Client();
        //Validamos
        try {
            if (name.equals(searchNameClient(name).toString())) {
                if (document == null) {
                    throw new Exception("*** DEBE INGRESAR EL N° DE DOCUMENTO ***");
                }
                if (document.toString().length() > 8) {
                    throw new Exception("*** EL DOCUMENTO DEBE CONTENER 8 NUMEROS SIN GUIONES ***");
                }
                if (name == null || name.trim().isEmpty()) {
                    throw new Exception("*** DEBE INGRESAR EL NOMBRE ***");
                }
                if (surname == null || surname.trim().isEmpty()) {
                    throw new Exception("*** DEBE INGRESAR EL APELLIDO ***");
                }
                if (phonenumber == null || surname.trim().isEmpty()) {
                    throw new Exception("*** DEBE INGRESAR EL N° DE TELEFONO ***");
                }
                if (phonenumber.length() != 10) {
                    throw new Exception("*** EL N° DE TELEFONO DEBE CONTENER 10 N° ***");
                }

                client.setDocument(document);
                client.setName(name);
                client.setSurname(surname);
                client.setPhonenumber(phonenumber);
                client.setId(UUID.randomUUID().toString()); //CADENA DE CARACTERES
                System.out.println("*** CLIENTE/A CREADO/A CON EXITO ***\n");
                return client;
            }
            if (!name.equals(searchNameClient(name).toString())) {
                return searchNameClient(name);
            }
            return client;
        } catch (Exception e) {
            System.out.println("*** NO SE GUARDO DE FORMA CORRECTA EL/LA CLIENTE/A ***" + e.getMessage());
            return null;
        }
    }
    
    public Client createClients(Long document, String name, String surname, String phonenumber) throws Exception {
        Client client = new Client();
        try {
            if (name.equals(searchNameClient(name).toString())) {
                if (document == null) {
                    throw new Exception("*** DEBE INGRESAR EL N° DE DOCUMENTO ***");
                }
                if (document.toString().length() > 8) {
                    throw new Exception("*** EL DOCUMENTO DEBE CONTENER 8 NUMEROS SIN GUIONES ***");
                }
                if (name == null || name.trim().isEmpty()) {
                    throw new Exception("*** DEBE INGRESAR EL NOMBRE ***");
                }
                if (surname == null || surname.trim().isEmpty()) {
                    throw new Exception("*** DEBE INGRESAR EL APELLIDO ***");
                }
                if (phonenumber == null || surname.trim().isEmpty()) {
                    throw new Exception("*** DEBE INGRESAR EL N° DE TELEFONO ***");
                }
                if (phonenumber.length() != 10) {
                    throw new Exception("*** EL N° DE TELEFONO DEBE CONTENER 10 N° ***");
                }

                client.setDocument(document);
                client.setName(name);
                client.setSurname(surname);
                client.setPhonenumber(phonenumber);
                client.setId(UUID.randomUUID().toString()); //CADENA DE CARACTERES
                System.out.println("*** CLIENTE/A CREADO/A CON EXITO ***\n");
                return client;
            }else{
                System.out.println("*** CLIENTE/A YA EXISTENTE ***");
            }
            return client;
        } catch (Exception e) {
            System.out.println("*** NO SE GUARDO DE FORMA CORRECTA EL/LA CLIENTE/A ***" + e.getMessage());
            return null;
        }
    }
    
    public void deleteAuthorforId(String id) throws Exception {
        try {
            //Validamos 
            if (id == null || id.trim().isEmpty()) {
                throw new Exception("*** DEBE INDICAR EL ID ***");
            }
            clientDAO.deleteClientforId(id);
            if (id != null && !id.trim().isEmpty()) {
                System.out.println("*** CLIENTE/A ELIMINADO POR ID CON EXITO ***");
            }
        } catch (Exception e) {
            throw e;
        }
    }
    
    public void deleteClientforName(String name) throws Exception {
        try {
            //Validamos 
            if (name == null || name.trim().isEmpty()) {
                throw new Exception("*** DEBE INDICAR EL NOMBRE ***");
            }
            clientDAO.deleteClientforSurname(name);
            if (name != null && !name.trim().isEmpty()) {
                System.out.println("*** CLIENTE/A ELIMINADO POR EL NOMBRE CON EXITO ***\n");
            }
        } catch (Exception e) {
            throw e;
        }
    }
    
    public void deleteClientforSurname(String surname) throws Exception {
        try {
            //Validamos 
            if (surname == null || surname.trim().isEmpty()) {
                throw new Exception("*** DEBE INDICAR EL APELLIDO ***");
            }
            clientDAO.deleteClientforSurname(surname);
            if (surname != null && !surname.trim().isEmpty()) {
                System.out.println("*** CLIENTE/A ELIMINADO POR EL APELLIDO CON EXITO ***\n");
            }
        } catch (Exception e) {
            throw e;
        }
    }
    
    public Client searchNameClient(String name) throws Exception {
        try {

            //Validamos
            if (name == null || name.trim().isEmpty()) {
                throw new Exception("Debe indicar el nombre del autxr");
            }

            Client client = clientDAO.searchforNameClient(name);
            return client;
        } catch (Exception e) {
            throw e;
        }
    }
    
    public List<Client> listClients() throws Exception {

        try {
            List<Client> clients = clientDAO.listClients();
            return clients;
        } catch (Exception e) {
            throw e;
        }
    }
    
    public void printAuthors() throws Exception {
        try {
            //Listamos los clientes
            List<Client> clients = listClients();

            //Imprimimos los libros - Solo algunos atributos....
            if (clients.isEmpty()) {
                throw new Exception("*** NO EXISTEN CLIENTES PARA IMPRIMIR ***");
            } else {
//                System.out.printf("%-50s%-12s%-15s\n", "ID", "NOMBRE", "ALTA");
                for (Client c : clients) {
                    System.out.println(c);
                }
                System.out.println("");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());;
        }
    }
    
    public void printforIdAuthor(String id) throws Exception {
        System.out.println(clientDAO.searchforIdClient(id));
    }
    
    public void printforNameClient(String name) throws Exception {
        System.out.println(clientDAO.searchforNameClient(name));
    }
    
    public void printforSurnameClient(String surname) throws Exception {
        System.out.println(clientDAO.searchforSurnameClient(surname));
    }
    
    public void printforPhonenumberClient(String phonenumber) throws Exception {
        System.out.println(clientDAO.searchforPhonenumberClient(phonenumber));
    }
}
