package Repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import Models.User;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

	Optional<User> findUserByEmail(String email);
	Optional<User> findUserByUsername(String username);
	
}
