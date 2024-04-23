package Repositories ;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import Models.Document;
import Models.User;

@Repository
public interface DocumentRepository extends JpaRepository<Document, Long> {
	Optional<Document> findDocumentByTitle(String Title);
	@Query("SELECT d FROM Document d WHERE d.user.id = :user_id ")
    List<Document> findDocumentByUser(@Param("user_id") Long user);

}
