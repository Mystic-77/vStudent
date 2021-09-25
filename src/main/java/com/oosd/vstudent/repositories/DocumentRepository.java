package com.oosd.vstudent.repositories;

import com.oosd.vstudent.models.Document;
import org.springframework.data.repository.CrudRepository;

public interface DocumentRepository extends CrudRepository<Document, Integer> {
}
