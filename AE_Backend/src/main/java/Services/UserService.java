package Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import Models.User;
import Repositories.UserRepository;
import lombok.AllArgsConstructor;


@Service
@AllArgsConstructor
public class UserService {

	@Autowired
	private final UserRepository user_repository ;
	
	public List<User> getUsers() {
		return user_repository.findAll() ;
	}
	
	 public User getUser(String username) {
	        Optional<User> userOptional = user_repository.findUserByUsername(username);
	        return userOptional.orElse(null);
	    }

	public User addNewUser(User user) {
		Optional<User> users =  user_repository.findUserByEmail(user.getEmail());
		if(users.isPresent()) {
			throw new IllegalStateException("Email taken") ;
		}
		Optional<User> usernames =  user_repository.findUserByUsername(user.getUsername());
		if(usernames.isPresent()) {
			throw new IllegalStateException("Username taken") ;
		}
		user_repository.save(user);
		return user ;
	}

	public boolean deleteUser(Long userId) {
		if(this.user_repository.existsById(userId)) {
			this.user_repository.deleteById(userId);
			return true ;
		}
		else {
			throw new IllegalStateException("User with id "+userId+" does not exists");
		}	
	}

	
	@Transactional
	public void updateUser(Long userId, String username, String email, String password) {
		 User user = this.user_repository.findById(userId)
                 .orElseThrow(() -> new IllegalStateException("User not found with ID: " + userId));
		
		if(username != null && !username.equals(user.getUsername()) && !username.isEmpty()) {
			Optional<User> usernames =  user_repository.findUserByUsername(username);
			if(usernames.isPresent()) {
				throw new IllegalStateException("Username taken") ;
			}
			user.setUsername(username);
		}
		
		if(email != null && !email.equals(user.getEmail()) &&  !email.isEmpty()) {
			Optional<User> users =  user_repository.findUserByEmail(email);
			if(users.isPresent()) {
				throw new IllegalStateException("Email taken") ;
			}
			user.setEmail(email);
		}
		if(password != null) {
			user.setPassword(password);
		}
	}	
}
