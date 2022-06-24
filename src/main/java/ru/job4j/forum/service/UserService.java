package ru.job4j.forum.service;

import org.springframework.stereotype.Service;
import ru.job4j.forum.model.User;
import ru.job4j.forum.repository.UserInMemRepo;

@Service
public class UserService {
    private final UserInMemRepo userRepo;

    public UserService(UserInMemRepo userRepo) {
        this.userRepo = userRepo;
    }

    public void add(User user) {
        userRepo.add(user);
    }

    public void delete(User user) {
        userRepo.delete(user);
    }
}
