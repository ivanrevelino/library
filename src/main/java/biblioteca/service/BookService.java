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
        return books;
    }

    public Book findByName(String name) {
        for (Book book : books) {
            if (book.getName().equals(name)) {
                return book;
            }
        }
        return null;
    }

    public Book add(Book book) {
        books.add(book);
        return book;
    }

    public void remove(String name) {
        books.remove(findByName(name));
    }
}
