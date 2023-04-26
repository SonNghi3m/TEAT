package g45_lexicon.teat.service;

import g45_lexicon.teat.exception.DataDuplicateException;
import g45_lexicon.teat.exception.DataNotFoundException;
import g45_lexicon.teat.model.dto.EmojiDto;
import g45_lexicon.teat.model.entity.Emoji;
import g45_lexicon.teat.repository.EmojiRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmojiServiceImpl implements EmojiService {
    @Autowired
    EmojiRepository emojiRepository;
    @Autowired
    ModelMapper modelMapper;

    @Override
    public List<EmojiDto> getAll() {
        List<Emoji> emojiList = emojiRepository.findAllByOrOrderByIdDesc();
        return modelMapper.map(emojiList, new TypeToken<List<EmojiDto>>() {
        }.getType());
    }

    @Override
    public EmojiDto findById(Integer id) {
        if (id == null) throw new IllegalArgumentException("Emoji id was null");
        Optional<Emoji> optionalEmoji = emojiRepository.findById(id);
        if (!optionalEmoji.isPresent()) throw new DataNotFoundException("Emoji id was not valid");
        Emoji emoji = optionalEmoji.get();
        return modelMapper.map(emoji, EmojiDto.class);
    }

    @Override
    public EmojiDto create(EmojiDto emojiDto) {
        if (emojiDto == null) throw new IllegalArgumentException("Emoji was null");
        if (emojiDto.getId() != 0) throw new IllegalArgumentException("Emoji id should be null or zero");
        if (emojiRepository.existsById(emojiDto.getId())) throw new DataDuplicateException("Emoji already existed");
        Emoji createdEntity = emojiRepository.save(modelMapper.map(emojiDto, Emoji.class));
        return modelMapper.map(createdEntity, EmojiDto.class);
    }

    @Override
    public void update(EmojiDto emojiDto) {
        if (emojiDto == null) throw new IllegalArgumentException("Emoji data was null");
        if (emojiDto.getId() == 0) throw new IllegalArgumentException("Emoji id should not be zero");
        if (!emojiRepository.findById(emojiDto.getId()).isPresent())
            throw new DataNotFoundException("Data not found error");
        if (emojiRepository.findByEmojiName(emojiDto.getEmojiName()).isPresent())
            throw new DataDuplicateException("Duplicate emojiName error");
        emojiRepository.save(modelMapper.map(emojiDto, Emoji.class));
    }

    @Override
    public void delete(Integer id) {
        EmojiDto emojiDto = findById(id);
        if (emojiDto == null) throw new DataNotFoundException("Id was not valid");
        emojiRepository.deleteById(id);
    }
}
