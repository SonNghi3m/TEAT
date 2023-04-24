package g45_lexicon.teat.service;

import g45_lexicon.teat.model.dto.EventDto;

import java.util.List;

public interface EventService {
    List<EventDto> getAll();
    EventDto findById(Integer id);
    EventDto create(EventDto obj);
    void update(EventDto obj);
    void delete(Integer id);
}
