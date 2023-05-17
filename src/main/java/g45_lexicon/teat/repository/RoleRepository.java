package g45_lexicon.teat.repository;

import g45_lexicon.teat.model.entity.Role;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface RoleRepository extends CrudRepository<Role, Integer> {
    Optional<Role> findByRoleTitle(String title);
}
