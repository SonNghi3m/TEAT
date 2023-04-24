package g45_lexicon.teat.service;

import g45_lexicon.teat.model.dto.RoleDto;

import java.util.List;

public interface RoleService {
    List<RoleDto> getAll();
    RoleDto findById(Integer id);
    RoleDto create(RoleDto obj);
    void update(RoleDto obj);
    void delete(Integer id);
}
