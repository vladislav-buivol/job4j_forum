package ru.job4j.forum.service;

import org.springframework.stereotype.Service;
import ru.job4j.forum.model.Post;
import ru.job4j.forum.repository.PostInMemRepo;

import java.util.ArrayList;
import java.util.List;

@Service
public class PostService {

    private final PostInMemRepo posts;

    public PostService(PostInMemRepo posts) {
        this.posts = posts;
    }

    public List<Post> getAll() {
        return new ArrayList<>(posts.getAll().values());
    }

    public void save(Post post) {
        if (posts.findById(post.getId()) == null) {
            posts.add(post);
        } else {
            posts.update(post);
        }
    }

    public void update(Post post) {
        posts.update(post);
    }

    public Post findById(int id) {
        return posts.findById(id);
    }

}