package g45_lexicon.teat.repository;

import g45_lexicon.teat.model.entity.Attachment;
import org.springframework.data.repository.CrudRepository;

public interface ConversationRepository extends CrudRepository<Attachment, Integer> {
}
