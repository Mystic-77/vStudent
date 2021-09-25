package com.oosd.vstudent.repositories;

import com.oosd.vstudent.models.Post;
import org.springframework.data.repository.CrudRepository;

public interface PostRepository extends CrudRepository<Post, Integer> {
}
