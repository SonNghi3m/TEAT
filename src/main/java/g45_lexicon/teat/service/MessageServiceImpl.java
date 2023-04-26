package g45_lexicon.teat.service;

import g45_lexicon.teat.exception.DataDuplicateException;
import g45_lexicon.teat.exception.DataNotFoundException;
import g45_lexicon.teat.model.dto.MessageDto;
import g45_lexicon.teat.model.entity.Message;
import g45_lexicon.teat.repository.MessageRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MessageServiceImpl implements MessageService {
    @Autowired
    MessageRepository messageRepository;
    @Autowired
    ModelMapper modelMapper;

    @Override
    public List<MessageDto> getAll() {
        List<Message> messageList = messageRepository.findAllByOrOrderByIdDesc();
        return modelMapper.map(messageList, new TypeToken<List<MessageDto>>() {
        }.getType());
    }

    @Override
    public MessageDto findById(Integer id) {
        if (id == null) throw new IllegalArgumentException("Message id was null");
        Optional<Message> optionalMessage = messageRepository.findById(id);
        if (!optionalMessage.isPresent()) throw new DataNotFoundException("Message id was not valid");
        Message message = optionalMessage.get();
        return modelMapper.map(message, MessageDto.class);
    }

    @Override
    public MessageDto create(MessageDto messageDto) {
        if (messageDto == null) throw new IllegalArgumentException("Message was null");
        if (messageDto.getId() != 0) throw new IllegalArgumentException("Message id should be null or zero");
        if (messageRepository.existsById(messageDto.getId())) throw new DataDuplicateException("Message already existed");
        Message createdEntity = messageRepository.save(modelMapper.map(messageDto, Message.class));
        return modelMapper.map(createdEntity, MessageDto.class);
    }

    @Override
    public void update(MessageDto messageDto) {
        if (messageDto == null) throw new IllegalArgumentException("Message data was null");
        if (messageDto.getId() == 0) throw new IllegalArgumentException("Message id should not be zero");
        if (!messageRepository.findById(messageDto.getId()).isPresent())
            throw new DataNotFoundException("Data not found error");
        messageRepository.save(modelMapper.map(messageDto, Message.class));
    }

    @Override
    public void delete(Integer id) {
        MessageDto messageDto = findById(id);
        if (messageDto == null) throw new DataNotFoundException("Id was not valid");
        messageRepository.deleteById(id);
    }
}
