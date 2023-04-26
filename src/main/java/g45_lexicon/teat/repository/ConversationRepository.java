package g45_lexicon.teat.repository;

import g45_lexicon.teat.model.entity.Attachment;
import g45_lexicon.teat.model.entity.Conversation;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ConversationRepository extends CrudRepository<Conversation, Integer> {
    List<Conversation> findAllByOrOrderByIdDesc();
    Optional<Conversation> findByChatName(String chatName);

}
