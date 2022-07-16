package ru.job4j.forum.model;


import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "coments")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "posts",
            referencedColumnName = "id")
    private Post post;
    private String author;
    private String text;
    private final LocalDateTime date = LocalDateTime.now().withNano(0);

    public Comment() {
    }

    public Comment(String author, String text) {
        this.author = author;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
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
