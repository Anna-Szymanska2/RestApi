package com.example.restapi.user;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

@Component
public class UserDaoService {
    private static List<User> users = new ArrayList<>();
    private static int countUsers = 0;

    static {
        users.add(new User(++countUsers,"Adam", LocalDate.now().minusYears(30)));
        users.add(new User(++countUsers,"Eve",LocalDate.now().minusYears(25)));
        users.add(new User(++countUsers,"Jimę",LocalDate.now().minusYears(20)));
    }

    public List<User> findAll() {
        return users;
    }
    public User findOne(int id){
        Predicate<? super User> predicate = user -> user.getId() == id;
        return users.stream().filter(predicate).findFirst().orElse(null);
    }

    public void deleteById(int id){
        Predicate<? super User> predicate = user -> user.getId() == id;
        users.removeIf(predicate);
    }

    public User save(User user){
        user.setId(++countUsers);
        users.add(user);
        return user;
    }
}
