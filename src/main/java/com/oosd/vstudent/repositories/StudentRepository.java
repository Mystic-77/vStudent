package com.oosd.vstudent.repositories;

import com.oosd.vstudent.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Integer> {
}
