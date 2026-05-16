package biblioteca.models;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class Loan {

    private User usuario;
    private List<Book> livros;

}
