package biblioteca.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Book {

    private String name;
    private String author;
    private int releaseYear;
    private boolean isOnLoan;

    public Book(String name, String author, int releaseYear) {
        this.name = name;
        this.author = author;
        this.releaseYear = releaseYear;
    }
}
