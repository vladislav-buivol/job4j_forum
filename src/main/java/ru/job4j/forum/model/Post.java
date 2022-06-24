package ru.job4j.forum.model;

import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Post {
    private int id;
    private String name;
    private String desc;
    private Set<Comment> comments = new HashSet<>();
    private final Calendar created = getCurrentDateTime();

    public static Post of(String name) {
        Post post = new Post();
        post.name = name;
        return post;
    }

    public Post() {
        this.id = 0;
    }

    private Calendar getCurrentDateTime() {
        Calendar calendar = Calendar.getInstance();
        LocalDateTime localDateTime = LocalDateTime.now();
        calendar.clear();
        calendar.set(localDateTime.getYear(), localDateTime.getMonthValue() - 1,
                localDateTime.getDayOfMonth(),
                localDateTime.getHour(), localDateTime.getMinute(), localDateTime.getSecond());
        return calendar;
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

    public String getDescription() {
        return desc;
    }

    public Post setDescription(String description) {
        this.desc = description;
        return this;
    }

    public Calendar getCreated() {
        return created;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Set<Comment> getComments() {
        return comments;
    }

    public void setComments(Set<Comment> comments) {
        this.comments = comments;
    }

    public Post addComment(Comment comment) {
        comments.add(comment);
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
                Objects.equals(desc, post.desc) &&
                Objects.equals(created, post.created);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, desc, created);
    }
}