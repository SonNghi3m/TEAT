package g45_lexicon.teat.service;

import g45_lexicon.teat.exception.DataDuplicateException;
import g45_lexicon.teat.exception.DataNotFoundException;
import g45_lexicon.teat.model.dto.RoleDto;

import java.util.List;

public interface RoleService {
    List<RoleDto> getAll();
    RoleDto findById(Integer id) throws DataNotFoundException;
    RoleDto findByTitle(String title) throws DataNotFoundException;
    RoleDto create(RoleDto obj) throws DataDuplicateException;
    RoleDto update(RoleDto obj) throws DataNotFoundException, DataDuplicateException;
    void delete(Integer id) throws DataNotFoundException;
}
