package g45_lexicon.teat.service;

import g45_lexicon.teat.exception.DataDuplicateException;
import g45_lexicon.teat.exception.DataNotFoundException;
import g45_lexicon.teat.model.dto.UserDto;
import g45_lexicon.teat.model.entity.User;
import g45_lexicon.teat.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    UserRepository userRepository;
    @Autowired
    ModelMapper modelMapper;

    @Override
    public List<UserDto> getAll() {
        List<User> userList = userRepository.findAllByOrOrderByIdDesc();
        return modelMapper.map(userList, new TypeToken<List<UserDto>>() {
        }.getType());
    }

    @Override
    public UserDto findById(Integer id) {
        if (id == null) throw new IllegalArgumentException("User id was null");
        Optional<User> optionalUser = userRepository.findById(id);
        if (!optionalUser.isPresent()) throw new DataNotFoundException("User id was not valid");
        User user = optionalUser.get();
        return modelMapper.map(user, UserDto.class);
    }

    @Override
    public UserDto create(UserDto userDto) {
        if (userDto == null) throw new IllegalArgumentException("User was null");
        if (userDto.getId() != 0) throw new IllegalArgumentException("User id should be null or zero");
        if (userRepository.existsById(userDto.getId())) throw new DataDuplicateException("User already existed");
        User createdEntity = userRepository.save(modelMapper.map(userDto, User.class));
        return modelMapper.map(createdEntity, UserDto.class);
    }

    @Override
    public void update(UserDto userDto) {
        if (userDto == null) throw new IllegalArgumentException("User data was null");
        if (userDto.getId() == 0) throw new IllegalArgumentException("User id should not be zero");
        if (!userRepository.findById(userDto.getId()).isPresent())
            throw new DataNotFoundException("Data not found error");
        if (userRepository.findByUsername(userDto.getUsername()).isPresent())
            throw new DataDuplicateException("Duplicate event name error");
        userRepository.save(modelMapper.map(userDto, User.class));
    }

    @Override
    public void delete(Integer id) {
        UserDto userDto = findById(id);
        if (userDto == null) throw new DataNotFoundException("Id was not valid");
        userRepository.deleteById(id);
    }
}
