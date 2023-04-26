package g45_lexicon.teat.service;

import g45_lexicon.teat.exception.DataDuplicateException;
import g45_lexicon.teat.exception.DataNotFoundException;
import g45_lexicon.teat.model.dto.ConversationDto;
import g45_lexicon.teat.model.entity.Conversation;
import g45_lexicon.teat.repository.ConversationRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ConversationServiceImpl implements ConversationService{
    @Autowired
    ConversationRepository conversationRepository;
    @Autowired
    ModelMapper modelMapper;

    @Override
    public List<ConversationDto> getAll() {
        List<Conversation> conversationList = conversationRepository.findAllByOrOrderByIdDesc();
        return modelMapper.map(conversationList, new TypeToken<List<ConversationDto>>(){}.getType());
    }

    @Override
    public ConversationDto findById(Integer id) {
        if (id == null) throw new IllegalArgumentException("Conversation id was null");
        Optional<Conversation> optionalConversation = conversationRepository.findById(id);
        if (!optionalConversation.isPresent()) throw new DataNotFoundException("Conversation id was not valid");
        Conversation conversation = optionalConversation.get();
        return modelMapper.map(conversation, ConversationDto.class);
    }

    @Override
    public ConversationDto create(ConversationDto conversationDto) {
        if (conversationDto == null) throw new IllegalArgumentException("Conversation was null");
        if (conversationDto.getId() != 0) throw new IllegalArgumentException("Conversation id should be null or zero");
        if (conversationRepository.existsById(conversationDto.getId())) throw new DataDuplicateException("Conversation already existed");
        Conversation createdEntity = conversationRepository.save(modelMapper.map(conversationDto, Conversation.class));
        return modelMapper.map(createdEntity, ConversationDto.class);
    }

    @Override
    public void update(ConversationDto conversationDto) {
        if (conversationDto == null) throw new IllegalArgumentException("Conversation data was null");
        if (conversationDto.getId() == 0) throw new IllegalArgumentException("Conversation id should not be zero");
        if (!conversationRepository.findById(conversationDto.getId()).isPresent())
            throw new DataNotFoundException("Data not found error");
        if (conversationRepository.findByChatName(conversationDto.getChatName()).isPresent())
            throw new DataDuplicateException("Duplicate chatName error");
        conversationRepository.save(modelMapper.map(conversationDto, Conversation.class));

    }

    @Override
    public void delete(Integer id) {
        ConversationDto conversationDto = findById(id);
        if (conversationDto == null) throw new DataNotFoundException("Id was not valid");
        conversationRepository.deleteById(id);

    }
}
