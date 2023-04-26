package g45_lexicon.teat.repository;

import g45_lexicon.teat.model.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Integer> {
    List<User> findAllByOrOrderByIdDesc();
    Optional<User> findByUsername(String username);

}
