package g45_lexicon.teat.repository;

import g45_lexicon.teat.model.entity.Emoji;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface EmojiRepository extends CrudRepository<Emoji, Integer> {
//    List<Emoji> findAllByOrOrderByIdDesc();
    Optional<Emoji> findByEmojiName(String emojiName);
}
