package biblioteca.service;

import biblioteca.models.Book;
import biblioteca.models.Loan;
import biblioteca.models.User;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class LoanService {

    public final Map<Long, List<Loan>> loans = new HashMap<>();
    public final UserService userService = new UserService();

    public Loan registerLoan(User user, List<Book> books) {

        List<String> unavailableBooks = books.stream().filter(Book::isOnLoan).map(Book::getName).toList();
        System.out.println("Unavailable books:\n" + unavailableBooks);

        List<Book> availableBooks = books.stream().filter(bk -> !bk.isOnLoan()).toList();

        if (availableBooks.isEmpty()) {
            throw new IllegalArgumentException("There is no book available for loan");
        }

        availableBooks.forEach(book -> book.setOnLoan(true));
        Loan loan = Loan.builder().books(availableBooks).user(user).id(user.getId()).loanDateTime(LocalDateTime.now()).build();
        user.getOwnBooks().addAll(availableBooks);
        loans.computeIfAbsent(user.getId(), k -> new ArrayList<>()).add(loan);

        return loan;
    }

    public void showAllLoans() {
        loans.values().forEach(System.out::println);
    }
}
