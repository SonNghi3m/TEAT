package g45_lexicon.teat.service;

import g45_lexicon.teat.exception.DataDuplicateException;
import g45_lexicon.teat.exception.DataNotFoundException;
import g45_lexicon.teat.model.dto.ConversationDto;
import g45_lexicon.teat.model.dto.MessageDto;
import g45_lexicon.teat.model.dto.UserDto;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ConversationService {
    List<ConversationDto> getAll();
    ConversationDto findById(Integer id) throws DataNotFoundException;
    List<ConversationDto> findByChatName(String chatName);
    ConversationDto create(ConversationDto conversationDto) throws DataDuplicateException, DataNotFoundException;
    ConversationDto update(ConversationDto conversationDto) throws DataNotFoundException, DataDuplicateException;
    void delete(Integer id) throws DataNotFoundException;
    ConversationDto addMessage(Integer conversationId, Integer messageId) throws DataNotFoundException, DataDuplicateException;
    ConversationDto removeMessage(Integer conversationId, Integer messageId) throws DataNotFoundException;
    ConversationDto addParticipant(Integer conversationId, Integer userId) throws DataNotFoundException, DataDuplicateException;
    ConversationDto removeParticipant(Integer conversationId, Integer userId) throws DataNotFoundException;
}
