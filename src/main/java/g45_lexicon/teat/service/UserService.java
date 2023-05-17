package g45_lexicon.teat.service;

import g45_lexicon.teat.exception.DataDuplicateException;
import g45_lexicon.teat.exception.DataNotFoundException;
import g45_lexicon.teat.model.dto.UserDto;

import java.util.List;

public interface UserService {
    List<UserDto> getAll();
    UserDto findById(Integer id) throws DataNotFoundException;
    UserDto findByUsername(String username) throws DataNotFoundException;
    UserDto findByEmail(String email) throws DataNotFoundException;
    UserDto create(UserDto userDto) throws DataDuplicateException, DataNotFoundException;
    UserDto update(UserDto userDto) throws DataNotFoundException, DataDuplicateException;
    void deleteById(Integer id) throws DataNotFoundException;
    void deleteByUsername(String username) throws DataNotFoundException;
    void deleteByEmail(String email) throws DataNotFoundException;
    //todo: change password

}
