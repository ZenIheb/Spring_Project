package Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import Models.Discussion;
import Repositories.DiscussionRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class DiscussionService {

	@Autowired
	public final DiscussionRepository discussionRepository ;
	

	public Optional<Discussion> getDiscussion(Long discussionId) {
		return discussionRepository.findById(discussionId);
	}


	public void addNewDiscussion(Discussion discussion) {
		discussionRepository.save(discussion);
	}


	public List<Discussion> getDiscussions() {
		return discussionRepository.findAll();
	}


	public void deleteDiscussion(Long discussionId) {
		if(discussionRepository.findById(discussionId).isPresent()) {
			discussionRepository.deleteById(discussionId);
		}
	}

	@Transactional
	public void update(Long discussionId, String title) {
		Discussion discussion = discussionRepository.findById(discussionId)
				.orElseThrow(() -> new IllegalStateException("Discussion not found with ID: " + discussionId));
		if(title != null && !title.isEmpty()) {
			discussion.setTitle(title);
		}
	}

}
