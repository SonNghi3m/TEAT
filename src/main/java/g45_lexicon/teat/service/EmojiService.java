package g45_lexicon.teat.service;

import g45_lexicon.teat.model.dto.EmojiDto;

import java.util.List;

public interface EmojiService {
    List<EmojiDto> getAll();
    EmojiDto findById(Integer id);
    EmojiDto create(EmojiDto obj);
    void update(EmojiDto obj);
    void delete(Integer id);
}
