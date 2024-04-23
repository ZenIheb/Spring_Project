package Controllers;

import java.util.List;
import java.util.Optional;

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

import Models.Discussion;
import Services.DiscussionService;
import lombok.AllArgsConstructor;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping(path="server/Discussions")
@AllArgsConstructor
public class DiscussionController {

	@Autowired
	private final DiscussionService discussionservice;

	@GetMapping
	public List<Discussion> getDiscussions() {
		return discussionservice.getDiscussions();
	}
	
	@GetMapping(path = "{discussionId}")
	public Optional<Discussion> getDiscussion(@PathVariable("discussionId") Long discussionId) {
		return discussionservice.getDiscussion(discussionId);
	}
	
	@PostMapping
	public void postDiscussion(@RequestBody Discussion discussion) {
		discussionservice.addNewDiscussion(discussion);
	}
	 
	@DeleteMapping(path = "{discussionId}")
	public void deleteDiscussion(@PathVariable("discussionId") Long discussionId) {
		discussionservice.deleteDiscussion(discussionId) ;
	}
	
	@PutMapping(path = "{discussionId}")
	public void updateTitle(@PathVariable("discussionId") Long discussionId,
							 @RequestParam(required = false) String title) {
		discussionservice.update(discussionId,title);
	}
}
