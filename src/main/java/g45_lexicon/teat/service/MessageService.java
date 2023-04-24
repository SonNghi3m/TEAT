package g45_lexicon.teat.service;

import g45_lexicon.teat.model.dto.MessageDto;

import java.util.List;

public interface MessageService {
    List<MessageDto> getAll();
    MessageDto findById(Integer id);
    MessageDto create(MessageDto obj);
    void update(MessageDto obj);
    void delete(Integer id);
}
