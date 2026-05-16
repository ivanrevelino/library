package biblioteca.service;

import biblioteca.models.User;

import java.util.ArrayList;
import java.util.List;

public class UserService {

    private final List<User> users = new ArrayList<>();

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

    public User add(User user) {
        users.add(user);
        return user;
    }

    public void remove(Long id) {
        users.remove(findById(id));
    }
}
