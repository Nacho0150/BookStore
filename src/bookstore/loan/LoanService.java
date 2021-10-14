package bookstore.loan;

import bookstore.book.Book;
import bookstore.client.Client;
import bookstore.client.ClientService;
import bookstore.persistence.LoanDAO;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;

public class LoanService {

    private final LoanDAO loanDAO;
    private final ClientService clientService;

    public LoanService() {
        this.loanDAO = new LoanDAO();
        this.clientService = new ClientService();
    }

    public void createLoan(LocalDate loanDate, LocalDate returnDate, Long document,
            String name, String surname, String phonenumber) {

        try {
            //Creamos el libro
            Loan loan = new Loan();

            Client newClient = clientService.createClient(document, name, surname, phonenumber);

            loan.setLoanDate(loanDate);
            loan.setReturnDate(returnDate);
            loan.setClient(newClient);
            loan.setId(UUID.randomUUID().toString());//CADENA DE CARACTERES

            loanDAO.saveLoan(loan);
        } catch (Exception e) {
            System.out.println("*** NO SE CREO EL PRESTAMO ***\n" + e.getMessage());
        }
    }

    public void modifyLoanDate(String id, LocalDate actualLoanDate, LocalDate newLoanDate) throws Exception {
        try {
            //Validamos
            if (id == null || id.trim().isEmpty()) {
                throw new Exception("*** DEBE INDICAR EL ID ***");
            }
            if (actualLoanDate == null) {
                throw new Exception("*** DEBE INDICAR LA FECHA ACTUAL QUE REALIZO EL PRESTAMO ***");
            }
            if (newLoanDate == null) {
                throw new Exception("*** DEBE INDICAR LA FECHA NUEVA QUE REALIZO EL PRESTAMO ***");
            }
            //Buscamos
            Loan loan = searchLoanId(id);

            //Validamos
            if (!loan.getLoanDate().toString().equals(actualLoanDate.toString())) {
                throw new Exception("*** LA FECHA ACTUAL NO ESTA ASOCIADO A NINGUN PRESTAMO ***");
            }
            //Modificamos
            loan.setLoanDate(newLoanDate);
            loanDAO.modifyLoan(loan);
        } catch (Exception e) {
            System.out.println(e.getMessage());;
        }
    }

    public void deleteClientforId(String id) throws Exception {
        try {
            //Validamos 
            if (id == null || id.trim().isEmpty()) {
                throw new Exception("*** DEBE INDICAR EL ID ***");
            }
            loanDAO.deleteLoanforId(id);
            if (id != null && !id.trim().isEmpty()) {
                System.out.println("*** CLIENTE ELIMINADO POR ID CON EXITO ***");
            }
        } catch (Exception e) {
            throw e;
        }
    }

    public Loan searchLoanId(String id) throws Exception {

        try {
            //Validamos
            if (id == null) {
                throw new Exception("Debe indicar el id del libro");
            }
            Loan loan = loanDAO.searchLoanId(id);
            return loan;
        } catch (Exception e) {
            throw e;
        }
    }

    public List<Loan> searchLoanforClientName(String name) throws Exception {
        try {

            //Validamos
            if (name == null || name.trim().isEmpty()) {
                throw new Exception("Debe indicar el autor");
            }

            List<Loan> loan = loanDAO.searchLoanforClientName(name);
            return loan;
        } catch (Exception e) {
            throw e;
        }
    }

    public List<Loan> listLoans() throws Exception {

        try {
            List<Loan> loans = loanDAO.listLoans();
            return loans;
        } catch (Exception e) {
            throw e;
        }
    }

    public void printLoans() throws Exception {
        try {
            //Listamos los prestamos
            List<Loan> loans = listLoans();

            //Imprimimos los prestamos - Solo algunos atributos....
            if (loans.isEmpty()) {
                throw new Exception("*** NO EXISTEN PRESTAMOS PARA IMPRIMIR ***");
            } else {
                System.out.println("*****************************************");
//                System.out.printf("%-50s%-35s%-35s%-35s%-20s%-35s%-35s%-15s%-15s%-15s\n", "ID", "ISBN", "Titulo", "Fecha de lanzamiento", "Ejemplares", "Ejemplares Prestados", "Ejemplares Restantes", "Alta", "Autor", "Editorial");
                for (Loan l : loans) {
                    System.out.println(l);
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

    public void printLoanforId(String id) throws Exception {
        System.out.println(loanDAO.searchLoanId(id));
    }

    public void printLoanforClientName(String name) throws Exception {
        try {
            List<Loan> loans = searchLoanforClientName(name);
            if (loans.isEmpty()) {
                throw new Exception("*** NO EXISTEN PRESTAMOS DE ESE CLIENTE ***");
            } else {
                System.out.println("*****************************************");
//                System.out.printf("%-50s%-35s%-35s%-35s%-20s\n", "ID", "ISBN", "Titulo", "Fecha de lanzamiento", "Autor");
                for (Loan l : loans) {
//                    System.out.println(b);
                    System.out.println(" ID: " + l.getId()
                            + "\n Fecha de creacion del Prestamo: " + l.getLoanDate()
                            + "\n Fecha de devolucion del Prestamo: " + l.getReturnDate()
                            + "\n Fecha de lanzamiento: " + l.getBooks()
                            + "\n Cliente: " + l.getClient().getName());
                }
                System.out.println("");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
