package g45_lexicon.teat.service;

import g45_lexicon.teat.exception.DataDuplicateException;
import g45_lexicon.teat.exception.DataNotFoundException;
import g45_lexicon.teat.model.dto.EventDto;

import java.util.List;

public interface EventService {
//    List<EventDto> getAll();
    EventDto findById(Integer id) throws DataNotFoundException;
    EventDto findByName(String name) throws DataNotFoundException;
    EventDto create(EventDto obj) throws DataDuplicateException;
    EventDto update(EventDto obj) throws DataNotFoundException, DataDuplicateException;
    void delete(Integer id) throws DataNotFoundException;
}
