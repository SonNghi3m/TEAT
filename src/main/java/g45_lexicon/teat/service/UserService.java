package g45_lexicon.teat.service;

import g45_lexicon.teat.model.dto.UserDto;

import java.util.List;

public interface UserService {
    List<UserDto> getAll();
    UserDto findById(Integer id);
    UserDto create(UserDto obj);
    void update(UserDto obj);
    void delete(Integer id);
}
