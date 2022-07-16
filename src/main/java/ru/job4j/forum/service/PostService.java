package ru.job4j.forum.service;

import org.springframework.stereotype.Service;
import ru.job4j.forum.model.Comment;
import ru.job4j.forum.model.Post;
import ru.job4j.forum.store.CommentRepository;
import ru.job4j.forum.store.PostRepository;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    private final PostRepository posts;
    private final CommentRepository comments;

    public PostService(PostRepository posts, CommentRepository comments) {
        this.posts = posts;
        this.comments = comments;
    }

    public List<Post> getAllByOrderByCreated() {
        return posts.findAllByOrderByCreated();
    }

    public void save(Post post) {
        posts.save(post);
    }

    public void update(Post post) {
        posts.save(post);
    }

    public Post findById(int id) {
        Optional<Post> op = posts.findById(id);
        return op.orElse(new Post());
    }

    public void saveComment(int postId, Comment comment) {
        findById(postId).addComment(comment);
        comment.setPost(findById(postId));
        comments.save(comment);
    }

}