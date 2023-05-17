package g45_lexicon.teat.repository;

import g45_lexicon.teat.model.entity.Attachment;
import g45_lexicon.teat.model.entity.Conversation;
import g45_lexicon.teat.model.entity.Message;
import g45_lexicon.teat.model.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ConversationRepository extends CrudRepository<Conversation, Integer> {
    List<Conversation> findByChatName(String chatName);
}
