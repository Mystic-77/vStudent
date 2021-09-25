package com.oosd.vstudent.repositories;

import com.oosd.vstudent.models.Document;
import org.springframework.data.jpa.repository.JpaRepository;


public interface DocumentRepository extends JpaRepository<Document, Integer> {
}
