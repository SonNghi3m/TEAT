package g45_lexicon.teat.service;

import g45_lexicon.teat.model.dto.ConversationDto;

import java.util.List;

public interface ConversationService {
    List<ConversationDto> getAll();
    ConversationDto findById(Integer id);
    ConversationDto create(ConversationDto obj);
    void update(ConversationDto obj);
    void delete(Integer id);
}
