package ru.job4j.forum.control;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
    public String regSave(@ModelAttribute User user, Model model) {
        if (userService.save(user, "ROLE_USER")) {
            return "redirect:login";
        }
        String errorMessge = String.format("User with name '%s' already exist", user.getUsername());
        model.addAttribute("errorMessge", errorMessge);
        return "reg";
    }

    @GetMapping("/reg")
    public String regPage() {
        return "reg";
    }
}
