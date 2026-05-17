package biblioteca.service;

import biblioteca.models.Book;
import biblioteca.models.User;

import java.util.ArrayList;
import java.util.List;

public class UserService {

    private final List<User> users = new ArrayList<>(
            List.of(
                    new User(1L, "Ivan", 12, "mbilaneivan@gmail.com"),
                    new User(2L, "Antonio", 21, "antonio22@gmail.com")
            )
    );

    public List<User> listAll(){
        return users;
    }

    public User findById(Long id) {
        for (User user : users) {
            if (user.getId().equals(id)){
                return user;
            }
        }
        return null;
    }

    public User addUser(User user) {
        users.add(user);
        return user;
    }

    public void addBooks(User user, List<Book> books) {
        User byId = findById(user.getId());
        byId.getOwnBooks().addAll(books);
    }

    public Book addBook(User user, Book book) {
        findById(user.getId()).getOwnBooks().add(book);
        return book;
    }

    public void remove(Long id) {
        users.remove(findById(id));
    }
}
