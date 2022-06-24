package ru.job4j.forum.model;


import org.springframework.security.core.context.SecurityContextHolder;

import java.time.LocalDateTime;
import java.util.Objects;

public class Comment {
    private String author;
    private String text = "";
    private final LocalDateTime date = LocalDateTime.now().withNano(0);

    public Comment() {
        this.author = SecurityContextHolder.getContext().getAuthentication().getName();
    }

    public Comment(String author, String text) {
        this.author = author;
        this.text = Objects.requireNonNullElse(text, "");
    }

    public Comment(String text) {
        this.text = text;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public LocalDateTime getDate() {
        return date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Comment)) {
            return false;
        }
        Comment comment = (Comment) o;
        return author.equals(comment.author) &&
                text.equals(comment.text) &&
                date.equals(comment.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(author, text, date);
    }
}
