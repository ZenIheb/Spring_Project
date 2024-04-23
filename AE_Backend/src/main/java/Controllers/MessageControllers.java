package Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Models.Message;
import Services.MessageService;
import lombok.AllArgsConstructor;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping(path = "server/users")
@AllArgsConstructor
public class MessageControllers {

	@Autowired
	private final MessageService messageservice;
	
//	@GetMapping(path = "{conversationId}/{userId}")
//	public List<Message> getUserMessages(@PathVariable("userId") Long userId,
//										 @PathVariable("conversationId") Long conversationId){
//		return this.messageservice.getUserMessages(userId,conversationId);
//	}
	
	@PostMapping(path = "messages")
	public void postMessage(@RequestBody Message message) {
		messageservice.addNewMessage(message);		
	}
}
