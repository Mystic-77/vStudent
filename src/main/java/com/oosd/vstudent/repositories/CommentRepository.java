package com.oosd.vstudent.repositories;

import com.oosd.vstudent.models.Comment;
import org.springframework.data.repository.CrudRepository;

public interface CommentRepository extends CrudRepository<Comment, Integer> {
}
