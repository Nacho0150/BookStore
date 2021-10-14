package bookstore.author;

import bookstore.book.Book;
import bookstore.persistence.AuthorDAO;
import java.util.List;
import java.util.UUID;

public class AuthorService {

    private final AuthorDAO authorDAO;
    private final Author author;

    public AuthorService() {
        this.authorDAO = new AuthorDAO();
        this.author = new Author();
    }

    public Author createAuthor(String name) {
        Author author = new Author();
        try {
            if (name.equals(searchNameAuthor(name).toString())) {
                author.setName(name);
                author.setId(UUID.randomUUID().toString());
                authorDAO.saveAuthor(author);
                System.out.println("*** AUTOR/A CREADO/A CON EXITO ***\n");
                return author;
            }
            if (!name.equals(searchNameAuthor(name).toString())) {
                return searchNameAuthor(name);
            }
            return author;
        } catch (Exception e) {
            System.out.println("*** NO SE GUARDO DE FORMA CORRECTA EL/LA AUTOR/A ***" + e.getMessage());
            return null;
        }
    }
    
    public Author createAuthors(String name) {
        Author author = new Author();
        try {
            if (name.equals(searchNameAuthor(name).toString())) {
                author.setName(name);
                author.setId(UUID.randomUUID().toString());
                authorDAO.saveAuthor(author);
                System.out.println("*** AUTOR/A CREADO/A CON EXITO ***\n");
                return author;
            }else{
                System.out.println("*** AUTOR/A YA EXISTENTE ***");
            }
            return author;
        } catch (Exception e) {
            System.out.println("*** NO SE GUARDO DE FORMA CORRECTA EL/LA AUTOR/A ***" + e.getMessage());
            return null;
        }
    }

    public void deleteAuthorforName(String name) throws Exception {
        try {
            //Validamos 
            if (name == null || name.trim().isEmpty()) {
                throw new Exception("Debe indicar el nombre del autxr");
            }
            authorDAO.deleteAuthorforName(name);
            if (name != null && !name.trim().isEmpty()) {
                System.out.println("*** AUTOR ELIMINADO CON EXITO ***\n");
            }
        } catch (Exception e) {
            throw e;
        }
    }

    public void deleteAuthorforId(String id) throws Exception {
        try {
            //Validamos 
            if (id == null || id.trim().isEmpty()) {
                throw new Exception("Debe indicar la ID");
            }
            authorDAO.deleteAuthorforId(id);
            if (id != null && !id.trim().isEmpty()) {
                System.out.println("*** AUTOR ELIMINADO POR ID CON EXITO ***");
            }
        } catch (Exception e) {
            throw e;
        }
    }

    public Author searchNameAuthor(String name) throws Exception {
        try {

            //Validamos
            if (name == null || name.trim().isEmpty()) {
                throw new Exception("Debe indicar el nombre del autxr");
            }

            Author author = authorDAO.searchNameAuthor(name);
            return author;
        } catch (Exception e) {
            throw e;
        }
    }

    public List<Author> listAuthors() throws Exception {

        try {
            List<Author> authors = authorDAO.listAuthors();
            return authors;
        } catch (Exception e) {
            throw e;
        }
    }

    public void printAuthors() throws Exception {
        try {
            //Listamos los libros
            List<Author> authors = listAuthors();

            //Imprimimos los libros - Solo algunos atributos....
            if (authors.isEmpty()) {
                throw new Exception("No existen libros para imprimir");
            } else {
//                System.out.printf("%-50s%-12s%-15s\n", "ID", "NOMBRE", "ALTA");
                for (Author a : authors) {
                    System.out.println(a);
                }
                System.out.println("");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());;
        }
    }

    public void printforNameAuthor(String name) throws Exception {
        System.out.println(authorDAO.searchNameAuthor(name));
    }

    public void printforIdAuthor(String id) throws Exception {
        System.out.println(authorDAO.searchIdAuthor(id));
    }
}
