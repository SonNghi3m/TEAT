package g45_lexicon.teat.repository;

import g45_lexicon.teat.model.entity.Permission;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PermissionRepository extends CrudRepository<Permission, Integer> {
    List<Permission> findAllByOrOrderByIdDesc();

}
