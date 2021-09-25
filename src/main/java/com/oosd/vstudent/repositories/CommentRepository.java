package com.oosd.vstudent.repositories;

import com.oosd.vstudent.models.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Integer> {
}
