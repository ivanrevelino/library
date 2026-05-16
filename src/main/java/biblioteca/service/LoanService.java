package biblioteca.service;

import biblioteca.models.Book;
import biblioteca.models.Loan;
import biblioteca.models.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LoanService {

    public final Map<User, Loan> loans = new HashMap<>();

    public Loan registerLoan(User user, List<Book> books) {
        Loan loan = Loan.builder()
                .books(books)
                .user(user)
                .build();
        user.getOwnBooks().addAll(books);
        loans.put(user, loan);
        return loan;
    }

    public void showAllLoans() {
        loans.values().forEach(System.out::println);
    }
}
