package com.dlunc.klbrt.persistence;

import com.dlunc.klbrt.persistence.model.Document;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DocumentRepo extends JpaRepository<Document, Long> {
}
