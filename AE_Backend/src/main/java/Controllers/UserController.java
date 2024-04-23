package Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import Models.User;
import Services.UserService;
import lombok.AllArgsConstructor;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping(path="server/users")
@AllArgsConstructor
public class UserController {
	
	@Autowired
	private final UserService userservice;
	
	
	@GetMapping()
	public List<User> getUsers(){
		return this.userservice.getUsers();
	}
	
	@GetMapping(path = "{username}" )
	public User getUser(@PathVariable("username") String username){
		return this.userservice.getUser(username);
	}
	
	@PostMapping
	public User posteUser(@RequestBody User user) {
		return this.userservice.addNewUser(user) ;
	}
	
	@DeleteMapping(path = "{userId}")
	public boolean deleteUser(@PathVariable("userId") Long userId) {
		return userservice.deleteUser(userId);
	}

	@PutMapping(path = "{userId}")
	public void updateUser(
				@PathVariable("userId") Long userId,
				@RequestParam(required = false) String username,
				@RequestParam(required = false) String email,
				@RequestParam(required = false) String password) {
		userservice.updateUser(userId,username,email,password);
	}
}
