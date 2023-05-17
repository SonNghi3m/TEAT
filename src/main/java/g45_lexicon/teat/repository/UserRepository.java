package g45_lexicon.teat.repository;

import g45_lexicon.teat.model.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Integer> {
    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);
    Optional<Void> deleteByUsername(String username);
    Optional<Void> deleteByEmail(String email);
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);


}
