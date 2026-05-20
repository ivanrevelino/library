package biblioteca.service;

import biblioteca.models.Book;
import biblioteca.models.Loan;
import biblioteca.models.User;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class LoanService {
    public final Map<Long, List<Loan>> loans = new HashMap<>();
    private static final BookService bookService = new BookService();

    public Loan registerLoan(User user, List<Book> books) {
        validateLoan(user, books);

        List<Book> availableBooksForLoan = bookService.getAvailableBooksForLoan(books);

        if (availableBooksForLoan.isEmpty()) throw new IllegalArgumentException("There is no book available for loan");

        Loan loan = createLoan(user, availableBooksForLoan);

        availableBooksForLoan.forEach(book -> book.setOnLoan(true));
        user.getOwnBooks().addAll(availableBooksForLoan);
        loans.computeIfAbsent(user.getId(), k -> new ArrayList<>()).add(loan);

        return loan;
    }

    private static Loan createLoan(User user, List<Book> availableBooks) {
        return Loan.builder()
                .books(availableBooks)
                .user(user)
                .id(UUID.randomUUID().toString()).
                loanDateTime(LocalDateTime.now())
                .build();
    }


    public List<Loan> getLoansByUserId(Long id) {
        if (id == null || id <= 0) throw new IllegalArgumentException("Invalid id");
        List<Loan> loansList = loans.get(id);
        if (loansList == null) throw new NoSuchElementException("Loan not found for user id: " + id);
        return loansList;
    }

    public void showAllLoans() {
        loans.values().forEach(System.out::println);
    }

    public void returnBook(Loan loan) {

        if (loan == null) {
            throw new IllegalArgumentException("Loan does not exist");
        }

        User user = loan.getUser();
        for (Book book : loan.getBooks()) {
            book.setOnLoan(false);
            user.getOwnBooks().remove(book);
        }

        loan.setReturned(true);
        loan.setReturnedDate(LocalDateTime.now());
    }

    private static void validateLoan(User user, List<Book> books) {
        if (books == null || books.isEmpty()) throw new IllegalArgumentException("Books list cannot be null or empty");

        if (user == null) throw new IllegalArgumentException("Invalid argument, User cant be null");
    }
}
