package g45_lexicon.teat.service;

import g45_lexicon.teat.exception.DataDuplicateException;
import g45_lexicon.teat.exception.DataNotFoundException;
import g45_lexicon.teat.model.dto.ConversationDto;
import g45_lexicon.teat.model.dto.MessageDto;
import g45_lexicon.teat.model.dto.UserDto;
import g45_lexicon.teat.model.entity.Conversation;
import g45_lexicon.teat.model.entity.Message;
import g45_lexicon.teat.model.entity.User;
import g45_lexicon.teat.repository.ConversationRepository;
import g45_lexicon.teat.repository.MessageRepository;
import g45_lexicon.teat.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ConversationServiceImpl implements ConversationService {
    ConversationRepository conversationRepository;
    ModelMapper modelMapper;
    UserRepository userRepository;
    MessageRepository messageRepository;

    @Autowired
    public ConversationServiceImpl(ConversationRepository conversationRepository, ModelMapper modelMapper, UserRepository userRepository, MessageRepository messageRepository) {
        this.conversationRepository = conversationRepository;
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
        this.messageRepository = messageRepository;
    }

    @Override
    public List<ConversationDto> getAll() {
        List<Conversation> list = new ArrayList<>();
        conversationRepository.findAll().iterator().forEachRemaining(list::add);
        return list.stream().map(category -> modelMapper.map(category, ConversationDto.class)).collect(Collectors.toList());
    }

    @Override
    public ConversationDto findById(Integer id) throws DataNotFoundException {
        if (id == null) throw new IllegalArgumentException("Conversation id was null!");
        Optional<Conversation> optionalConversation = conversationRepository.findById(id);
        if (!optionalConversation.isPresent()) throw new DataNotFoundException("Conversation id was not found!");
        Conversation conversation = optionalConversation.get();
        return modelMapper.map(conversation, ConversationDto.class);
    }

    @Override
    public List<ConversationDto> findByChatName(String chatName) {
        if (chatName == null) throw new IllegalArgumentException("Chat name was null!");
        List<Conversation> list = new ArrayList<>();
        conversationRepository.findByChatName(chatName).iterator().forEachRemaining(list::add);
        return list.stream().map(category -> modelMapper.map(category, ConversationDto.class)).collect(Collectors.toList());
    }

    @Override
    @Transactional(rollbackFor = {Exception.class})
    public ConversationDto create(ConversationDto conversationDto) throws DataDuplicateException, DataNotFoundException {
        if (conversationDto == null) throw new IllegalArgumentException("Conversation was null!");
        if (conversationDto.getId() != null) throw new IllegalArgumentException("Conversation id should be automatically generated!");
        Conversation result = conversationRepository.save(modelMapper.map(conversationDto, Conversation.class));
        return modelMapper.map(result, ConversationDto.class);
    }

    @Override
    @Transactional(rollbackFor = {Exception.class})
    public ConversationDto update(ConversationDto conversationDto) throws DataNotFoundException, DataDuplicateException {
        if (conversationDto == null) throw new IllegalArgumentException("Conversation data was null!");
        if (conversationDto.getId() == null || conversationDto.getId() == 0) throw new IllegalArgumentException("Conversation id should not be zero!");
        if (!conversationRepository.findById(conversationDto.getId()).isPresent()) throw new DataNotFoundException("Conversation was not found!");
        Conversation result = conversationRepository.save(modelMapper.map(conversationDto, Conversation.class));
        return modelMapper.map(result, ConversationDto.class);
    }

    @Override
    public void delete(Integer id) throws DataNotFoundException {
        ConversationDto conversationDto = findById(id);
        if (conversationDto == null) throw new DataNotFoundException("Id was not valid!");
        conversationRepository.deleteById(id);
    }

    @Override
    public ConversationDto addMessage(Integer conversationId, Integer messageId) throws DataNotFoundException, DataDuplicateException {
        if (messageId == null) throw new IllegalArgumentException("Message id was null!");
        if (conversationId == null) throw new IllegalArgumentException("Conversation id was null!");
        Optional<Conversation> optionalConversation = conversationRepository.findById(conversationId);
        if (!optionalConversation.isPresent()) throw new DataNotFoundException("Conversation was not found!");
        Optional<Message> optionalMessage = messageRepository.findById(messageId);
        if (!optionalMessage.isPresent()) throw new DataNotFoundException("Message was not found!");
        Conversation conversation = optionalConversation.get();
        Message message = optionalMessage.get();
        if (!conversation.getParticipants().contains(message.getSender())) throw new DataNotFoundException("Message sender is not in this group!");
        if (message.getConversation() != null) throw new IllegalArgumentException("Message belongs to another conversation!");
        conversation.addMessage(message);
        conversationRepository.save(conversation);

        //todo: how to notifyAll() here?
        return modelMapper.map(conversation, ConversationDto.class);
    }

    @Override
    public ConversationDto addParticipant(Integer conversationId, Integer userId) throws DataNotFoundException, DataDuplicateException {
        if (userId == null) throw new IllegalArgumentException("User id was null!");
        if (conversationId == null) throw new IllegalArgumentException("Conversation id was null!");
        Optional<Conversation> optionalConversation = conversationRepository.findById(conversationId);
        if (!optionalConversation.isPresent()) throw new DataNotFoundException("Conversation was not found!");
        Optional<User> optionalUser = userRepository.findById(userId);
        if (!optionalUser.isPresent()) throw new DataNotFoundException("User was not found!");
        Conversation conversation = optionalConversation.get();
        User user = optionalUser.get();
        List<User> participants = conversation.getParticipants();
        if (participants.contains(user)) throw new DataDuplicateException("User is already a participant in the conversation!");
        List<Conversation> conversations = user.getConversations();
        if (conversations.contains(conversation)) throw new DataDuplicateException("Conversation is already present in user's conversations!");
        conversation.addParticipant(user);
        conversationRepository.save(conversation);
        return modelMapper.map(conversation, ConversationDto.class);
    }


    @Override
    public ConversationDto removeMessage(Integer conversationId, Integer messageId) throws DataNotFoundException {
        if (messageId == null) throw new IllegalArgumentException("Message id was null!");
        if (conversationId == null) throw new IllegalArgumentException("Conversation id was null!");
        Optional<Conversation> optionalConversation = conversationRepository.findById(conversationId);
        if (!optionalConversation.isPresent()) throw new DataNotFoundException("Conversation was not found!");
        Optional<Message> optionalMessage = messageRepository.findById(messageId);
        if (!optionalMessage.isPresent()) throw new DataNotFoundException("Message was not found!");
        Conversation conversation = optionalConversation.get();
        Message message = optionalMessage.get();
        conversation.removeMessage(message);
        conversationRepository.save(conversation);
        return modelMapper.map(conversation, ConversationDto.class);
    }

    @Override
    public ConversationDto removeParticipant(Integer conversationId, Integer userId) throws DataNotFoundException {
        if (userId == null) throw new IllegalArgumentException("User id was null!");
        if (conversationId == null) throw new IllegalArgumentException("Conversation id was null!");
        Optional<Conversation> optionalConversation = conversationRepository.findById(conversationId);
        if (!optionalConversation.isPresent()) throw new DataNotFoundException("Conversation was not found!");
        Optional<User> optionalUser = userRepository.findById(userId);
        if (!optionalUser.isPresent()) throw new DataNotFoundException("User was not found!");
        Conversation conversation = optionalConversation.get();
        User user = optionalUser.get();
        conversation.removeParticipant(user);
        return modelMapper.map(conversation, ConversationDto.class);
    }
}
