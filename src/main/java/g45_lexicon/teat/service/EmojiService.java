package g45_lexicon.teat.service;

import g45_lexicon.teat.exception.DataDuplicateException;
import g45_lexicon.teat.exception.DataNotFoundException;
import g45_lexicon.teat.model.dto.EmojiDto;

import java.util.List;

public interface EmojiService {
//    List<EmojiDto> getAll();
    EmojiDto findById(Integer id) throws DataNotFoundException;
    EmojiDto findByName(String name) throws DataNotFoundException;
    EmojiDto create(EmojiDto obj) throws DataDuplicateException;
    EmojiDto update(EmojiDto obj) throws DataNotFoundException, DataDuplicateException;
    void delete(Integer id) throws DataNotFoundException;
}
