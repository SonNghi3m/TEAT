package g45_lexicon.teat.service;

import g45_lexicon.teat.model.dto.PermissionDto;

import java.util.List;

public interface PermissionService {
    List<PermissionDto> getAll();
    PermissionDto findById(Integer id);
    PermissionDto create(PermissionDto obj);
    void update(PermissionDto obj);
    void delete(Integer id);
}
