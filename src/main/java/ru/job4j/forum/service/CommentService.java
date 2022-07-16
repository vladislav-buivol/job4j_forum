package ru.job4j.forum.service;

import org.springframework.stereotype.Service;
import ru.job4j.forum.model.Comment;
import ru.job4j.forum.store.CommentRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CommentService {
    private final CommentRepository comments;

    public CommentService(CommentRepository comments) {
        this.comments = comments;
    }

    public List<Comment> getAll() {
        return comments.findAll();
    }

    public void save(Comment comment) {
        comments.save(comment);
    }

    public void update(Comment comment) {
        comments.save(comment);
    }

    public List<Comment> findByPostAndOrderByDateDesc(int postId) {
        return comments.findByPostOrderByDate(postId);
    }

    public Comment findById(int id) {
        Optional<Comment> op = comments.findById(id);
        return op.orElse(new Comment());
    }
}
