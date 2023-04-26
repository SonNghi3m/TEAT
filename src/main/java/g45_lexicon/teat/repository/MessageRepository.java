package g45_lexicon.teat.repository;

import g45_lexicon.teat.model.entity.Emoji;
import g45_lexicon.teat.model.entity.Message;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface MessageRepository extends CrudRepository<Message, Integer> {
    List<Message> findAllByOrOrderByIdDesc();
}
