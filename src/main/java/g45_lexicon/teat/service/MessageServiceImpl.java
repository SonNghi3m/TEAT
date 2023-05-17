package g45_lexicon.teat.service;

import g45_lexicon.teat.exception.DataDuplicateException;
import g45_lexicon.teat.exception.DataNotFoundException;
import g45_lexicon.teat.model.dto.MessageDto;
import g45_lexicon.teat.model.entity.Message;
import g45_lexicon.teat.repository.ConversationRepository;
import g45_lexicon.teat.repository.MessageRepository;
import g45_lexicon.teat.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MessageServiceImpl implements MessageService {
    MessageRepository messageRepository;
    ModelMapper modelMapper;
    ConversationRepository conversationRepository;
    UserRepository userRepository;

    @Autowired
    public MessageServiceImpl(MessageRepository messageRepository, ModelMapper modelMapper, UserRepository userRepository, ConversationRepository conversationRepository) {
        this.messageRepository = messageRepository;
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
        this.conversationRepository = conversationRepository;
    }

    @Override
    public List<MessageDto> getAll() {
        List<Message> list = new ArrayList<>();
        messageRepository.findAll().iterator().forEachRemaining(list::add);
        return list.stream().map(category -> modelMapper.map(category, MessageDto.class)).collect(Collectors.toList());
    }

    @Override
    public MessageDto findById(Integer id) throws DataNotFoundException {
        if (id == null) throw new IllegalArgumentException("Message id was null!");
        Optional<Message> optionalMessage = messageRepository.findById(id);
        if (!optionalMessage.isPresent()) throw new DataNotFoundException("Message id was not found!");
        Message message = optionalMessage.get();
        return modelMapper.map(message, MessageDto.class);
    }

    @Override
    public  List<MessageDto> findBySender(String username) {
        if (username == null) throw new IllegalArgumentException("Username was null!");
        List<Message> list = new ArrayList<>();
        messageRepository.findBySender(username).iterator().forEachRemaining(list::add);
        return list.stream().map(category -> modelMapper.map(category, MessageDto.class)).collect(Collectors.toList());
    }

    @Override
    @Transactional(rollbackFor = {Exception.class})
    public MessageDto create(MessageDto messageDto) throws DataDuplicateException, DataNotFoundException {
        if (messageDto == null) throw new IllegalArgumentException("Message was null!");
        if (messageDto.getId() != null) throw new IllegalArgumentException("Message id should be automatically generated!");
        if (messageDto.isDeletedStatus() || messageDto.isEditedStatus()) throw new IllegalArgumentException("Edit and delete status should be 'false' at the beginning!");
        Message createdEntity = messageRepository.save(modelMapper.map(messageDto, Message.class));
        createdEntity.setEditedStatus(false);
        createdEntity.setDeletedStatus(false);
        return modelMapper.map(createdEntity, MessageDto.class);
    }

    @Override
    @Transactional(rollbackFor = {Exception.class})
    public MessageDto update(MessageDto messageDto) throws DataNotFoundException {
        if (messageDto == null) throw new IllegalArgumentException("Message data was null!");
        if (messageDto.getId() == null || messageDto.getId() == 0) throw new IllegalArgumentException("Message id should not be zero or null!");
        Optional<Message> optionalMessage = messageRepository.findById(messageDto.getId());
        if (!optionalMessage.isPresent()) throw new DataNotFoundException("Message was not found!");
        if (!userRepository.existsByUsername(messageDto.getSender().getUsername())) throw new DataNotFoundException("Sender was not found!");
        String previousTextContent = optionalMessage.get().getTextContent();
        Message result = messageRepository.save(modelMapper.map(messageDto, Message.class));
        if (messageDto.getTextContent() == null) result.setDeletedStatus(true);
        if (!Objects.equals(messageDto.getTextContent(), previousTextContent)) result.setEditedStatus(true);
        return modelMapper.map(result, MessageDto.class);
    }

    @Override
    public void deleteById(Integer id) throws DataNotFoundException {
        MessageDto messageDto = findById(id);
        if (messageDto == null) throw new DataNotFoundException("Id was not valid!");
        messageRepository.deleteById(id);
    }

    @Override
    public void deleteAll() {
        messageRepository.deleteAll();
    }


}
