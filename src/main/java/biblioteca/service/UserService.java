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

    public User addBooks(User user, List<Book> books) {
        if (user == null || user.getId() == null) {
            throw new IllegalArgumentException("Usuario Invalido");
        }

        if (books == null || books.isEmpty()) throw new IllegalArgumentException("A lista de livros esta vazia ou nao existe");

        User userFindById = findById(user.getId());
        if (userFindById == null) {
            throw new RuntimeException("Usuario nao existe");
        }

        if (userFindById.getOwnBooks() == null) {
            userFindById.setOwnBooks(new ArrayList<>());
        }

        userFindById.getOwnBooks().addAll(books);
        return userFindById;
    }

    public void remove(Long id) {
        users.remove(findById(id));
    }
}
