package bookstore.book;

import bookstore.author.Author;
import bookstore.author.AuthorService;
import bookstore.persistence.BookDAO;
import bookstore.publishing.Publisher;
import bookstore.publishing.PublisherService;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;

public class BookService {

    // RECORDAR QUE SIEMPRE, LA LOGICA DEBE ESTAR EN EL SERVICIO CORRESPONDIENTE
    private final BookDAO bookDAO;
    private final PublisherService publisherService;
    private final AuthorService authorService;

    public BookService() {
        this.bookDAO = new BookDAO();
        this.publisherService = new PublisherService();
        this.authorService = new AuthorService();
    }

    public void createBook(Long isbn, String title, Calendar yeaar, String nameAuthor,
            String namePublisher) throws Exception {

        try {
            //Validamos
            if (isbn == null) {
                throw new Exception("Debe ingresar el ISBN");
            }
            if (isbn.toString().length() > 17) {
                throw new Exception("El ISBN tiene que ser 17 espacios(numeros y guiones)");
            }
            if (title == null || title.trim().isEmpty()) {
                throw new Exception("Debe indicar el titulo");
            }

            //Creamos el libro
            Book book = new Book();

            Author newAuthor = authorService.createAuthor(nameAuthor);
            Publisher newPublisher = publisherService.createPublisher(namePublisher);

            book.setIsbn(isbn);
            book.setTitle(title);
            book.setYeaar(yeaar);
            book.setAuthor(newAuthor);
            book.setPublisher(newPublisher);
            book.setId(UUID.randomUUID().toString());//CADENA DE CARACTERES

            bookDAO.saveBook(book);
        } catch (Exception e) {
            System.out.println("*** NO SE CREO EL LIBRO ***\n" + e.getMessage());
        }
    }

    public void modifyISBN(String id, Long actualIsbn, Long newIsbn) throws Exception {
        try {
            //Validamos
            if (id == null || id.trim().isEmpty()) {
                throw new Exception("Debe indicar la ID");
            }
            if (actualIsbn == null) {
                throw new Exception("Debe ingresar el ISBN");
            }
            if (actualIsbn.toString().length() > 17) {
                throw new Exception("El ISBN tiene que ser 17 espacios(numeros y guiones)");
            }
            if (newIsbn == null) {
                throw new Exception("Debe ingresar el ISBN");
            }
            if (newIsbn.toString().length() > 17) {
                throw new Exception("El ISBN tiene que ser 17 espacios(numeros y guiones)");
            }
            //Buscamos
            Book book = searchBookId(id);

            //Validamos
            if (!book.getIsbn().toString().equals(actualIsbn.toString())) {
                throw new Exception("El ISBN autual no es el mismo asociado al titulo ingresado");
            }
            //Modificamos
            book.setIsbn(newIsbn);
            bookDAO.modifyBook(book);
        } catch (Exception e) {
            System.out.println(e.getMessage());;
        }
    }

    public void deleteBookforIsbn(Long isbn) throws Exception {
        try {
            //Validamos 
            if (isbn == null) {
                throw new Exception("Debe ingresar el ISBN");
            }
            if (isbn.toString().length() > 17) {
                throw new Exception("El ISBN tiene que ser 17 espacios(numeros y guiones)");
            }
            bookDAO.deleteBookISBN(isbn);
            if (isbn != null && isbn.toString().length() < 18) {
                System.out.println("*** LIBRO ELIMINADO POR ISBN CON EXITO ***");
            }
        } catch (Exception e) {
            throw e;
        }
    }

    public void deleteBookId(String id) throws Exception {
        try {
            //Validamos 
            if (id == null || id.trim().isEmpty()) {
                throw new Exception("Debe indicar la ID");
            }
            bookDAO.deleteBookId(id);
            if (id != null && !id.trim().isEmpty()) {
                System.out.println("*** LIBRO ELIMINADO POR ID CON EXITO ***");
            }
        } catch (Exception e) {
            throw e;
        }
    }

    public void deleteBookforTitle(String title) throws Exception {
        try {
            //Validamos 
            if (title == null || title.trim().isEmpty()) {
                throw new Exception("Debe indicar el titulo");
            }
            bookDAO.deleteBookTitle(title);
            if (title != null && !title.trim().isEmpty()) {
                System.out.println("*** LIBRO ELIMINADO POR TITULO CON EXITO ***");
            }
        } catch (Exception e) {
            throw e;
        }
    }

    public List<Book> searchBookforAuthorName(String name) throws Exception {
        try {

            //Validamos
            if (name == null || name.trim().isEmpty()) {
                throw new Exception("Debe indicar el autor");
            }

            List<Book> book = bookDAO.searchBookAuthorName(name);
            return book;
        } catch (Exception e) {
            throw e;
        }
    }

    public List<Book> searchBookPublisherName(String name) throws Exception {
        try {

            //Validamos
            if (name == null || name.trim().isEmpty()) {
                throw new Exception("Debe indicar la editorial");
            }

            List<Book> book = bookDAO.searchBookPublisherName(name);
            return book;
        } catch (Exception e) {
            throw e;
        }
    }

    public Book searchBookId(String id) throws Exception {

        try {
            //Validamos
            if (id == null) {
                throw new Exception("Debe indicar el id del libro");
            }
            Book book = bookDAO.searchBookId(id);
            return book;
        } catch (Exception e) {
            throw e;
        }
    }

    public List<Book> listBooks() throws Exception {

        try {
            List<Book> books = bookDAO.listBooks();
            return books;
        } catch (Exception e) {
            throw e;
        }
    }

    public void printBooks() throws Exception {
        try {
            //Listamos los libros
            List<Book> books = listBooks();

            //Imprimimos los libros - Solo algunos atributos....
            if (books.isEmpty()) {
                throw new Exception("No existen libros para imprimir");
            } else {
                System.out.println("*****************************************");
//                System.out.printf("%-50s%-35s%-35s%-35s%-20s%-35s%-35s%-15s%-15s%-15s\n", "ID", "ISBN", "Titulo", "Fecha de lanzamiento", "Ejemplares", "Ejemplares Prestados", "Ejemplares Restantes", "Alta", "Autor", "Editorial");
                for (Book b : books) {
                    System.out.println(b);
                    System.out.println("");
//                    System.out.println(" ISBN:" + b.getIsbn() +
//                                   "\n Titulo:" + b.getTitle() +
//                                   "\n Fecha de lanzamiento:" + b.getYeaar() +
//                                   "\n Autor:" + b.getAuthor() +
//                                   "\n Editorial:" + b.getPublisher());
                }
                System.out.println("");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());;
        }
    }

    public void printforTitleBook(String title) throws Exception {
        System.out.println(bookDAO.searchBookTitle(title));
    }

    public void printforIsbnBook(Long isbn) throws Exception {
        System.out.println(bookDAO.searchBookIsbn(isbn));
    }

    public void printforIdBook(String id) throws Exception {
        System.out.println(bookDAO.searchBookId(id));
    }

    public void printBookforAuthorName(String name) throws Exception {
        try {
            List<Book> books = searchBookforAuthorName(name);
            if (books.isEmpty()) {
                throw new Exception("No existen libros de ese Autor para imprimir");
            } else {
                System.out.println("*****************************************");
//                System.out.printf("%-50s%-35s%-35s%-35s%-20s\n", "ID", "ISBN", "Titulo", "Fecha de lanzamiento", "Autor");
                for (Book b : books) {
//                    System.out.println(b);
                    System.out.println(" ID: " + b.getId()
                            + "\n ISBN: " + b.getIsbn()
                            + "\n Titulo: " + b.getTitle()
                            + "\n Fecha de lanzamiento: " + b.getYeaar().get(Calendar.YEAR)
                            + "\n Autor: " + b.getAuthor().getName()
                            + "\n Editorial: " + b.getPublisher().getName());
                }
                System.out.println("");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void printBookforPublisherName(String name) throws Exception {
        try {
            List<Book> books = searchBookPublisherName(name);
            if (books.isEmpty()) {
                throw new Exception("No existen libros de esa Editorial para imprimir");
            } else {
                System.out.println("*****************************************");
//                System.out.printf("%-50s%-35s%-35s%-35s%-20s\n", "ID", "ISBN", "Titulo", "Fecha de lanzamiento", "Autor");
                for (Book b : books) {
//                    System.out.println(b);
                    System.out.println(" ID: " + b.getId()
                            + "\n ISBN: " + b.getIsbn()
                            + "\n Titulo: " + b.getTitle()
                            + "\n Fecha de lanzamiento: " + b.getYeaar().get(Calendar.YEAR)
                            + "\n Autor: " + b.getAuthor().getName()
                            + "\n Editorial: " + b.getPublisher().getName());
                }
                System.out.println("");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
//    public void cargarMascotaUsuario(String apodo,String raza,String idUsuario) throws Exception{
//          Usuario usuario = daoUsuario.buscarUsuarioPorId(idUsuario);
//          Mascota nueva = mascotaService.crearMascota(apodo, raza);
//          usuario.setMascota(nueva);
//          daoUsuario.modificarUsuario(usuario);// Utilizo el metodo del DAO           
//    }
}
