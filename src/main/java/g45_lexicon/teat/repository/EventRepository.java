package g45_lexicon.teat.repository;

import g45_lexicon.teat.model.entity.Emoji;
import g45_lexicon.teat.model.entity.Event;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface EventRepository extends CrudRepository<Event, Integer> {
//    List<Event> findAllByOrOrderByIdDesc();
    Optional<Event> findByEventName(String eventName);
}
