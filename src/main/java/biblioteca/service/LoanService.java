package biblioteca.service;

import biblioteca.models.Book;
import biblioteca.models.Loan;
import biblioteca.models.User;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

public class LoanService {
    public final Map<Long, List<Loan>> loans = new HashMap<>();

    public Loan registerLoan(User user, List<Book> books) {

        if (books == null || books.isEmpty()) throw new IllegalArgumentException("Invalid argument");

        if (user == null) throw new IllegalArgumentException("Invalid argument, User cant be null");

        Map<Boolean, List<Book>> collectedBooks = books.stream().collect(Collectors.partitioningBy(Book::isOnLoan));

        List<Book> unavailableBooks = collectedBooks.get(true);

        List<Book> availableBooks = collectedBooks.get(false);

        System.out.println(unavailableBooks);

        if (availableBooks.isEmpty()) {
            throw new IllegalArgumentException("There is no book available for loan");
        }

        Loan loan = Loan.builder()
                .books(availableBooks)
                .user(user)
                .id(UUID.randomUUID().toString()).
                loanDateTime(LocalDateTime.now())
                .build();

        availableBooks.forEach(book -> book.setOnLoan(true));
        user.getOwnBooks().addAll(availableBooks);
        loans.computeIfAbsent(user.getId(), k -> new ArrayList<>()).add(loan);

        return loan;
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

        // seta os livros como nao emprestados e remove os livros do usuario que fazem parte do emprestimo
        for (Book book : loan.getBooks()) {
            book.setOnLoan(false);
            user.getOwnBooks().remove(book);
        }

        loan.setReturned(true);
        loan.setReturnedDate(LocalDateTime.now());
    }
}
