package biblioteca.service;

import biblioteca.models.Book;

import java.util.ArrayList;
import java.util.List;

public class BookService {

    private final List<Book> books = new ArrayList<>(
            List.of(
                    new Book("O sapo nao lava o pe", "O Sapo cantor", 2014),
                    new Book("Oque eu aprendi a nao ser", "Sr Alguem", 2021),
                    new Book("Someone girl", "Sr Banana" ,2020)
            )
    );

    public List<Book> listAll() {
        // evita alguem mandar listAll().clear
        return new ArrayList<>(books);
    }

    public Book findByName(String name) {

        if (name == null) throw new IllegalArgumentException("Invalid argument, name cannot be null");

        for (Book book : books) {
            if (book.getName().equals(name)) {
                return book;
            }
        }
        return null;
    }

    public Book add(Book book) {
        if (book == null) throw new IllegalArgumentException("Book cannot be null");
        books.add(book);
        return book;
    }

    public void remove(String name) {
        if (name == null) throw new IllegalArgumentException("Name cannot be null");
        Book bookToRemove = findByName(name);
        if (bookToRemove == null) throw new IllegalArgumentException("No book found with the given name");
        books.remove(bookToRemove);
    }
}