package g45_lexicon.teat.service;

import g45_lexicon.teat.exception.DataDuplicateException;
import g45_lexicon.teat.exception.DataNotFoundException;
import g45_lexicon.teat.model.dto.PermissionDto;

import java.util.List;

public interface PermissionService {
    List<PermissionDto> getAll();
    PermissionDto findById(Integer id) throws DataNotFoundException;
    PermissionDto findByName(String name) throws DataNotFoundException;
    PermissionDto create(PermissionDto obj) throws DataDuplicateException;
    PermissionDto update(PermissionDto obj) throws DataNotFoundException;
    void delete(Integer id) throws DataNotFoundException;
}
