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
        return new ArrayList<>(users);
    }

    public User findById(Long id) {
        if (id == null || id <= 0) throw new IllegalArgumentException("Invalid id");
        for (User user : users) {
            if (user.getId().equals(id)){
                return user;
            }
        }
        return null;
    }

    public User addUser(User user) {
        if (user == null) throw new IllegalArgumentException("User cannot be null");
        users.add(user);
        return user;
    }

    public void remove(Long id) {
        User userToRemove = findById(id);
        if (userToRemove == null) throw new IllegalArgumentException("User not found with id " + id);
        users.remove(userToRemove);
    }
}
