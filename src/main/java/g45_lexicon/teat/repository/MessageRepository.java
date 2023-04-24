package g45_lexicon.teat.repository;

import g45_lexicon.teat.model.entity.Message;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MessageRepository extends CrudRepository<Message, Integer> {
    List<Message> findAllByOrOrderByIdDesc();

}
