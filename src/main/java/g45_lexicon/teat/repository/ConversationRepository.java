package g45_lexicon.teat.repository;

import g45_lexicon.teat.model.entity.Conversation;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ConversationRepository extends CrudRepository<Conversation, Integer> {
    List<Conversation> findAllByOrOrderByIdDesc();

}
