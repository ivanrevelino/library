package biblioteca.tests;

import biblioteca.models.Book;
import biblioteca.models.Loan;
import biblioteca.models.User;
import biblioteca.service.BookService;
import biblioteca.service.LoanService;
import biblioteca.service.UserService;

import java.util.List;

public class TestsEnvironment {
    public static void main(String[] args) {
        List<Book> books = new BookService().listAll();
        User user = new UserService().findById(1L);

        LoanService loanService = new LoanService();
        Loan loan = loanService.registerLoan(user, books);
        System.out.println(loan);
    }
}
