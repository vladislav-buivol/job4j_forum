package ru.job4j.forum.repository;

import ru.job4j.forum.model.Post;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class PostInMemRepo {
    private final AtomicInteger id = new AtomicInteger(1);
    private HashMap<Integer, Post> posts = new HashMap<>();

    public PostInMemRepo() {
    }

    public Map<Integer, Post> getAll() {
        return Collections.unmodifiableMap(posts);
    }

    public void add(Post post) {
        post.setId(id.getAndIncrement());
        posts.putIfAbsent(post.getId(), post);
    }

    public void delete(int id) {
        posts.remove(id);
    }

    public void update(Post post) {
        findById(post.getId())
                .setDescription(post.getDescription())
                .setName(post.getName());
    }

    public Post findById(int id) {
        return posts.get(id);
    }

    public void delete(Post post) {
        posts.remove(post);
    }

}
