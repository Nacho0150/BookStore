package bookstore.book;

import bookstore.author.Author;
import bookstore.publishing.Publisher;
import java.time.LocalDate;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-10-02T21:49:04")
@StaticMetamodel(Book.class)
public class Book_ { 

    public static volatile SingularAttribute<Book, Boolean> toregister;
    public static volatile SingularAttribute<Book, Integer> copies;
    public static volatile SingularAttribute<Book, Author> author;
    public static volatile SingularAttribute<Book, Long> isbn;
    public static volatile SingularAttribute<Book, Integer> remainingcopies;
    public static volatile SingularAttribute<Book, Publisher> publisher;
    public static volatile SingularAttribute<Book, String> id;
    public static volatile SingularAttribute<Book, String> title;
    public static volatile SingularAttribute<Book, LocalDate> yeaar;
    public static volatile SingularAttribute<Book, Integer> borrowedcopies;

}