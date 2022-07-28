package ru.job4j.forum.control;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.job4j.forum.model.User;
import ru.job4j.forum.service.UserService;

@Controller
public class RegController {
    private final UserService userService;

    public RegController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/reg")
    public String regSave(@ModelAttribute User user) {
        userService.save(user, "ROLE_USER");
        return "redirect:login";
    }

    @GetMapping("/reg")
    public String regPage() {
        return "reg";
    }
}
