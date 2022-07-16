package ru.job4j.forum.model;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "posts")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String description;

    @OneToMany(fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            mappedBy = "post",
            orphanRemoval = true
    )
    private List<Comment> comments = new ArrayList<>();
    private final LocalDateTime created = LocalDateTime.now().withNano(0);

    public static Post of(String name) {
        Post post = new Post();
        post.name = name;
        return post;
    }

    public Post() {
        this.id = 0;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Post setName(String name) {
        this.name = name;
        return this;
    }

    public Post setDescription(String description) {
        this.description = description;
        return this;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public String getDescription() {
        return description;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public Post addComment(Comment comment) {
        comments.add(comment);
        comment.setPost(this);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Post post = (Post) o;
        return id == post.id &&
                Objects.equals(name, post.name) &&
                Objects.equals(description, post.description) &&
                Objects.equals(created, post.created);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, created);
    }
}