package com.oosd.vstudent.repositories;

import com.oosd.vstudent.models.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository extends JpaRepository<Tag, Integer> {
}
