package biblioteca.models;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class Loan {

    private Long id;
    private User user;
    private List<Book> books;
    private boolean returned;
    private LocalDateTime loanDateTime;
    private  LocalDateTime returnedDate;

}
