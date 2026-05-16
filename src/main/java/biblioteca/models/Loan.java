package biblioteca.models;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class Loan {

    private Long id;
    private User user;
    private List<Book> books;

}
