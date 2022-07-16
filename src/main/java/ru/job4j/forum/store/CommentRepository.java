package ru.job4j.forum.store;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.job4j.forum.model.Comment;

import java.util.List;
import java.util.Optional;

@Repository
public interface CommentRepository extends CrudRepository<Comment, Integer> {

    List<Comment> findAll();

    Optional<Comment> findById(Integer id);

    @Query("select c from Comment c where c.post.id = (?1) order by c.date")
    List<Comment> findByPostOrderByDate(final int postId);
}
