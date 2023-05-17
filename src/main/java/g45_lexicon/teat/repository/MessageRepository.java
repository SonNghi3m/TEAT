package g45_lexicon.teat.repository;

import g45_lexicon.teat.model.entity.Message;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface MessageRepository extends CrudRepository<Message, Integer> {
   @Query("SELECT m FROM Message m WHERE m.sender.username = :un")
   List<Message> findBySender(@Param("un") String username);

}
