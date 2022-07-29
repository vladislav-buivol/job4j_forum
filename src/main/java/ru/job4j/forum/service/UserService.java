package ru.job4j.forum.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.job4j.forum.model.User;
import ru.job4j.forum.store.AuthorityRepository;
import ru.job4j.forum.store.UserRepository;

@Service
public class UserService {
    private final UserRepository userRepo;
    private final AuthorityRepository authorityRepo;
    private final PasswordEncoder encoder;

    public UserService(UserRepository userRepo, AuthorityRepository authorityRepo,
                       PasswordEncoder encoder) {
        this.userRepo = userRepo;
        this.authorityRepo = authorityRepo;
        this.encoder = encoder;
    }

    public boolean save(User user, String role) {
        if (userRepo.findByUsername(user.getUsername()) == null) {
            user.setEnabled(true);
            user.setPassword(encoder.encode(user.getPassword()));
            user.setAuthority(authorityRepo.findByAuthority(role));
            userRepo.save(user);
            return true;
        }
        return false;
    }

    public void delete(User user) {
        userRepo.delete(user);
    }
}
