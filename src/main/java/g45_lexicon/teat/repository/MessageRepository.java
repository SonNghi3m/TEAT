package g45_lexicon.teat.repository;

import g45_lexicon.teat.model.entity.Attachment;
import org.springframework.data.repository.CrudRepository;

public interface MessageRepository extends CrudRepository<Attachment, Integer> {
}
