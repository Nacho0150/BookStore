package bookstore;

import bookstore.author.Author;
import bookstore.author.AuthorService;
import bookstore.book.BookService;
import bookstore.loan.LoanService;
import bookstore.publishing.PublisherService;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Scanner;

public class Menu {

    private final Scanner sc = new Scanner(System.in).useDelimiter("\n");
    private final BookService bookService;
    private final AuthorService authorService;
    private final PublisherService publisherService;
    private final LoanService loanService;

    public Menu() {
        this.bookService = new BookService();
        this.authorService = new AuthorService();
        this.publisherService = new PublisherService();
        this.loanService = new LoanService();
    }

    public void menu() {
        String opc;
        try {
            do {
                System.out.println("********* Menu de opciones *********");
                System.out.println("A)Crear Libro(con Autor y Editorial)");
                System.out.println("B)Crear Autor");
                System.out.println("C)Crear Editorial");
                System.out.println("D)Crear Cliente");
                System.out.println("E)Crear Prestamo(con cliente)");
                System.out.println("F)Buscar Autor por el Nombre");
                System.out.println("G)Búsqueda de un Libro por ISBN");
                System.out.println("H)Búsqueda de un Libro por Título");
                System.out.println("I)Búsqueda de un Libro/s por Nombre de Autor");
                System.out.println("J)Búsqueda de un Libro/s por Nombre de Editorial");
                System.out.println("K)Mostrar Libros ingresados");
                System.out.println("L)Mostrar Autores ingresados");
                System.out.println("M)Mostrar Editoriales ingresadas");
                System.out.println("N)Eliminar Libro por el Titulo");
                System.out.println("O)Eliminar Autor por su Nombre");
                System.out.println("P)Eliminar Autor por su ID");
                System.out.println("Q)Eliminar Editorial por su Nombre");
                System.out.println("R)Eliminar Editorial por su ID");
                System.out.println("S)Salir");
                opc = sc.next().toUpperCase();

                switch (opc) {
                    case "A":
                        bookService.createBook(loadIsbn(), loadTitle(), loadYear(), loadAuthor(), loadPublisher());
                        break;
                    case "B":
                        authorService.createAuthors(loadAuthors());
                        break;
                    case "C":
                        publisherService.createPublisher(loadPublisher());
                        break;
                    case "D":
                        
                        break;
                    case "E":
                        loanService.createLoan(loadLoanDate(), null, Long.MIN_VALUE, opc, opc, opc);
                        break;
                    case "F":
                        authorService.printforNameAuthor(searchforNameAuthor());
                        break;
                    case "G":
                        bookService.printforIsbnBook(searchforIsbnBook());
                        break;
                    case "H":
                        bookService.printforTitleBook(searchBookforTitle());
                        break;
                    case "I":
                        bookService.printBookforAuthorName(searchBookforAuthorName());
                        break;
                    case "J":
                        bookService.printBookforPublisherName(searchBookforPublisherName());
                        break;
                    case "K":
                        bookService.printBooks();
                        break;
                    case "L":
                        authorService.printAuthors();
                        break;
                    case "M":
                        publisherService.printPublishers();
                        break;
                    case "N":
                        bookService.deleteBookforTitle(deleteBookforTitle());
                        break;
                    case "O":
                        authorService.deleteAuthorforName(deleteAuthorforName());
                        break;
                    case "P":
                        authorService.deleteAuthorforId(deleteAuthorforId());
                        break;
                    case "Q":
                        publisherService.deletePublisherforName(deletePublisherforName());
                        break;
                    case "R":
                        publisherService.deletePublisherforId(deletePublisherforId());
                        break;
                    case "S":
                        break;
                }
            } while (!opc.equals("S"));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("*** INGRESE BIEN LA OPCION ***");
        }
    }
    /**
     * OPC A
     * @return 
     */
    public Long loadIsbn() {
        try {
            Long isbn;
            System.out.println("Ingrese el ISBN");
            isbn = sc.nextLong();
//            System.out.println(isbn.toString().length());
            return isbn;
        } catch (NumberFormatException e) {
            System.out.println("Ingrese bien el ISBN");
            return null;
        }
    }
    
    /**
     * OPC A
     * @return 
     */
    public String loadTitle() {
        try {
            String title;
            System.out.println("Ingrese el titulo");
            title = sc.next();
            return title;
        } catch (Exception e) {
            System.out.println("Ingrese bien el titulo");
            return null;
        }
    }
    
    /**
     * OPC A
     * @return 
     */
    public Calendar loadYear() {
        try {
            Calendar yeaar = Calendar.getInstance();
            System.out.println("Ingrese el año");
            int year = sc.nextInt();
            yeaar.set(Calendar.YEAR, year);
            return yeaar;
        } catch (Exception e) {
            System.out.println("*** INGRESE BIEN EL AÑO ***");
            return null;
        }
    }
    
    /**
     * OPC A
     * @return 
     */
    public Integer loadCopies() {
        try {
            Integer copies;
            System.out.println("Ingrese cuantos ejemplares hay de ese libro");
            copies = sc.nextInt();
            return copies;
        } catch (Exception e) {
            System.out.println("*** INGRESE BIEN EL NUMERO DE EJEMNPLARES ***");
            return null;
        }
    }
    
    /**
     * OPC A
     * @return 
     */
    public String loadAuthor() {
        try {
            System.out.println("*****************************************");
            if (!authorService.listAuthors().isEmpty()) {
                System.out.println("Elija un autor de la lista o ingrese uno nuevo");
                authorService.printAuthors();
            }
            String nameAuthor;
            System.out.println("Ingrese el Nombre del Autor");
            nameAuthor = sc.next();
            return nameAuthor;
        } catch (Exception e) {
            System.out.println("Ingrese bien el Nombre");
            return null;
        }
    }
    
    /**
     * OPC B
     * @return 
     */
    public String loadAuthors() {
        try {
            System.out.println("*****************************************");
            String nameAuthor;
            System.out.println("Ingrese el Nombre del Autor nuevo");
            nameAuthor = sc.next();
            return nameAuthor;
        } catch (Exception e) {
            System.out.println("Ingrese bien el Nombre");
            return null;
        }
    }
    
    /**
     * OPC A
     * @return 
     */
    public String loadPublisher() {
        try {
            System.out.println("*****************************************");
            if (!publisherService.listPublishers().isEmpty()) {
                System.out.println("Elija una Editorial de la lista o ingrese uno nueva");
                publisherService.printPublishers();
            }
            String namePublisher;
            System.out.println("Ingrese el Nombre de la Editorial");
            namePublisher = sc.next();
            return namePublisher;
        } catch (Exception e) {
            System.out.println("Ingrese bien el Nombre");
            return null;
        }
    }
    
    /**
     * OPC D
     * @return 
     */
    public String searchforNameAuthor() {
        try {
            String name;
            System.out.println("Ingrese el nombre de el Autor a buscar");
            name = sc.next();
            return name;
        } catch (Exception e) {
            System.out.println("Ingrese bien el nombre");
            return null;
        }
    }
    
    /**
     * OPC E
     * @return 
     */
    public LocalDate loadLoanDate() {
        try {
            LocalDate year = LocalDate.now();
            return year;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
    
    /**
     * OPC E
     * @return 
     */
    public LocalDate loadReturnDate() {
        try {
            LocalDate year = LocalDate.now();
            return year;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
    
    /**
     * OPC E
     * @return 
     */
    public Long searchforIsbnBook() {
        try {
            Long isbn;
            System.out.println("Ingrese el ISBN del libro a buscar");
            isbn = sc.nextLong();
//            System.out.println(isbn.toString().length());
            return isbn;
        } catch (NumberFormatException e) {
            System.out.println("Ingrese bien el ISBN");
            return null;
        }
    }
    
    /**
     * OPC F
     * @return 
     */
    public String searchBookforTitle() {
        try {
            String titleBook;
            System.out.println("Ingrese el nombre de el libro a buscar");
            titleBook = sc.next();
            return titleBook;
        } catch (Exception e) {
            System.out.println("Ingrese bien el nombre");
            return null;
        }
    }
    
    /**
     * OPC G
     * @return 
     */
    public String searchBookforAuthorName() {
        try {
            String authorName;
            System.out.println("Ingrese el nombre de el autor para buscar el libro");
            authorName = sc.next();
            return authorName;
        } catch (Exception e) {
            System.out.println("Ingrese bien el nombre del autor");
            return null;
        }
    }
    
    /**
     * OPC H
     * @return 
     */
    public String searchBookforPublisherName() {
        try {
            String publisherName;
            System.out.println("Ingrese el nombre de la editorial para buscar el libro");
            publisherName = sc.next();
            return publisherName;
        } catch (Exception e) {
            System.out.println("Ingrese bien el nombre de la editorial");
            return null;
        }
    }
    
    /**
     * OPC L
     * @return 
     */
    public String deleteBookforTitle() {
        try {
            String title;
            System.out.println("Ingrese el nombre de el libro a eliminar");
            title = sc.next();
            return title;
        } catch (Exception e) {
            System.out.println("Ingrese bien el nombre de el titulo");
            return null;
        }
    }
    
    /**
     * OPC M
     * @return 
     */
    public String deleteAuthorforName() {
        try {
            String name;
            System.out.println("Ingrese el Nombre de el Autor");
            name = sc.next();
            return name;
        } catch (Exception e) {
            System.out.println("Ingrese bien el Nombre de el Autor");
            return null;
        }
    }
    
    /**
     * OPC N
     * @return 
     */
    public String deleteAuthorforId() {
        try {
            String id;
            System.out.println("Ingrese el ID de el Autor");
            id = sc.next();
            return id;
        } catch (Exception e) {
            System.out.println("Ingrese bien el ID de el Autor");
            return null;
        }
    }
    
    /**
     * OPC P
     * @return 
     */
    public String deletePublisherforName() {
        try {
            String name;
            System.out.println("Ingrese el Nombre de la Editorial");
            name = sc.next();
            return name;
        } catch (Exception e) {
            System.out.println("*** INGRESE BIEN EL NOMBRE DE LA EDITORIAL");
            return null;
        }
    }
    
    /**
     * OPC P
     * @return 
     */
    public String deletePublisherforId() {
        try {
            String id;
            System.out.println("Ingrese el ID de la Editorial");
            id = sc.next();
            return id;
        } catch (Exception e) {
            System.out.println("*** INGRESE BIEN EL NOMBRE DE LA EDITORIAL");
            return null;
        }
    }
    
}
