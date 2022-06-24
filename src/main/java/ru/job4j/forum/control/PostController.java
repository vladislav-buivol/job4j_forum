package ru.job4j.forum.control;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.job4j.forum.model.Comment;
import ru.job4j.forum.model.Post;
import ru.job4j.forum.service.PostService;

import javax.servlet.http.HttpServletRequest;

@Controller
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/post")
    public String postPage(@RequestParam("id") int id, Model model) {
        model.addAttribute("post", postService.findById(id));
        return "post";
    }

    @GetMapping("/create")
    public String createPost(Model model) {
        model.addAttribute("post", new Post());
        return "edit";
    }

    @GetMapping("/edit")
    public String editPost(@RequestParam("id") int id, Model model) {
        model.addAttribute("post", postService.findById(id));
        return "edit";
    }

    @PostMapping("/save")
    public String addPost(@ModelAttribute Post post) {
        postService.save(post);
        return "redirect:/";
    }

    @PostMapping("/comment")
    public String addComment(@ModelAttribute Comment comment, HttpServletRequest req) {
        int id = Integer.parseInt(req.getParameter("id"));
        if (comment.getText().length() != 0) {
            comment.setAuthor(SecurityContextHolder.getContext().getAuthentication().getName());
            postService.findById(id).addComment(comment);
        }
        return "redirect:/post?id=" + id;
    }

}
