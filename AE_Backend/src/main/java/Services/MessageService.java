package Services;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Models.Message;
import Repositories.MessageRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class MessageService {

	@Autowired
	private final MessageRepository messageRepository ;
	
	
	public List<Message> getUserMessages(Long userId,Long discussionId) {
		return messageRepository.findMessagesByUserIdAndDiscussionId(userId,discussionId);
	}


	public void addNewMessage(Message message) {
		message.setDate(LocalDate.now());
		messageRepository.save(message);
	}

}
