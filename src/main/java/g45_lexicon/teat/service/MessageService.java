package g45_lexicon.teat.service;

import g45_lexicon.teat.exception.DataDuplicateException;
import g45_lexicon.teat.exception.DataNotFoundException;
import g45_lexicon.teat.model.dto.MessageDto;

import java.util.List;

public interface MessageService {
    List<MessageDto> getAll();
    MessageDto findById(Integer id) throws DataNotFoundException;
    List<MessageDto> findBySender(String username) throws DataNotFoundException;
    MessageDto create(MessageDto obj) throws DataDuplicateException, DataNotFoundException;
    MessageDto update(MessageDto obj) throws DataNotFoundException;
    void deleteById(Integer id) throws DataNotFoundException;
    void deleteAll();
}
