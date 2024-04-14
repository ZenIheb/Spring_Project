package Repositories ;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import Models.Document;

@Repository
public interface DocumentRepository extends JpaRepository<Document, Long> {
	Optional<Document> findDocumentByTitle(String Title);
}
