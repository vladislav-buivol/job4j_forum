package ru.job4j.forum.store;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.job4j.forum.model.Post;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepository extends CrudRepository<Post, Integer> {

    List<Post> findAllByOrderByCreated();

    Optional<Post> findById(Integer id);
}