package g45_lexicon.teat.repository;

import g45_lexicon.teat.model.entity.Event;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface EventRepository extends CrudRepository<Event, Integer> {
    List<Event> findByEventName(String eventName);
}
