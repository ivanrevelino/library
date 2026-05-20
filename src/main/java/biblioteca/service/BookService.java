package biblioteca.service;

import biblioteca.models.Book;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BookService {

    private final List<Book> books = new ArrayList<>(
            List.of(
                    new Book("O sapo nao lava o pe", "O Sapo cantor", 2014),
                    new Book("Oque eu aprendi a nao ser", "Sr Alguem", 2021),
                    new Book("Someone girl", "Sr Banana" ,2020)
            )
    );

    public List<Book> listAll() {
        return new ArrayList<>(books);
    }

    public Book findByName(String name) {
        validateNotNull(name, "Invalid argument, name cannot be null");
        for (Book book : books) {
            if (book.getName().equals(name)) {
                return book;
            }
        }
        return null;
    }

    public Book add(Book book) {
        validateNotNull(book, "Book cannot be null");
        books.add(book);
        return book;
    }

    public void remove(String name) {
        validateNotNull(name, "Name cannot be null");
        Book bookToRemove = findByName(name);
        validateNotNull(bookToRemove, "No book found with the given name");
        books.remove(bookToRemove);
    }

    public List<Book> getAvailableBooksForLoan(List<Book> books) {
        validateNotNull(books, "Books cannot be null");
        Map<Boolean, List<Book>> collectedBooks = books.stream().collect(Collectors.partitioningBy(Book::isOnLoan));
        return collectedBooks.get(false);
    }

    private static <T> void validateNotNull(T t, String message) {
        if (t == null) throw new IllegalArgumentException(message);
    }
}