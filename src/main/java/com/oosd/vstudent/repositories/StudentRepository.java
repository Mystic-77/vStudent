package com.oosd.vstudent.repositories;

import com.oosd.vstudent.models.Student;
import org.springframework.data.repository.CrudRepository;

public interface StudentRepository extends CrudRepository<Student, Integer> {
}
