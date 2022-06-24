package ru.job4j.forum.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.forum.model.User;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class UserInMemRepo {
    private final AtomicInteger id = new AtomicInteger(1);
    private HashMap<Integer, User> users = new HashMap<>();

    public Map<Integer, User> getAll() {
        return Collections.unmodifiableMap(users);
    }

    public void add(User user) {
        user.setId(id.getAndIncrement());
        users.putIfAbsent(user.getId(), user);
    }

    public void delete(User user) {
        users.remove(user);
    }
}
