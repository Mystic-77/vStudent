package com.oosd.vstudent.repositories;

import com.oosd.vstudent.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PostRepository extends JpaRepository<Post, Integer> {
}
