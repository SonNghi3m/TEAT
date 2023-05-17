package g45_lexicon.teat.service;

import g45_lexicon.teat.exception.DataDuplicateException;
import g45_lexicon.teat.exception.DataNotFoundException;
import g45_lexicon.teat.model.dto.UserDto;
import g45_lexicon.teat.model.entity.Role;
import g45_lexicon.teat.model.entity.User;
import g45_lexicon.teat.repository.MessageRepository;
import g45_lexicon.teat.repository.RoleRepository;
import g45_lexicon.teat.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    UserRepository userRepository;
    ModelMapper modelMapper;
    RoleRepository roleRepository;
    MessageRepository messageRepository;
    @Autowired
    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper, RoleRepository roleRepository, MessageRepository messageRepository) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.roleRepository = roleRepository;
        this.messageRepository = messageRepository;
    }

    @Override
    public List<UserDto> getAll() {
        List<User> list = new ArrayList<>();
        userRepository.findAll().iterator().forEachRemaining(list::add);
        return list.stream().map(category -> modelMapper.map(category, UserDto.class)).collect(Collectors.toList());
    }

    @Override
    public UserDto findById(Integer id) throws DataNotFoundException {
        if (id == null) throw new IllegalArgumentException("User id was null!");
        Optional<User> optionalUser = userRepository.findById(id);
        if (!optionalUser.isPresent()) throw new DataNotFoundException("User id was not valid!");
        User user = optionalUser.get();
        return modelMapper.map(user, UserDto.class);
    }

    @Override
    public UserDto findByUsername(String username) throws DataNotFoundException {
        if (username == null) throw new IllegalArgumentException("Username was null!");
        Optional<User> optionalUser = userRepository.findByUsername(username);
        if (!optionalUser.isPresent()) throw new DataNotFoundException("Username not found error!");
        User user = optionalUser.get();
        return modelMapper.map(user, UserDto.class);
    }

    @Override
    public UserDto findByEmail(String email) throws DataNotFoundException {
        if (email == null) throw new IllegalArgumentException("Email was null!");
        Optional<User> optionalUser = userRepository.findByEmail(email);
        if (!optionalUser.isPresent()) throw new DataNotFoundException("Email not found error!");
        User user = optionalUser.get();
        return modelMapper.map(user, UserDto.class);
    }

    @Override
    @Transactional(rollbackFor = {Exception.class})
    public UserDto create(UserDto userDto) throws DataDuplicateException, DataNotFoundException {
        if (userDto == null) throw new IllegalArgumentException("User was null!");
        if (userDto.getId() != null) throw new IllegalArgumentException("User id should be automatically generated!");
        if (userDto.getFirstName() == null) throw new IllegalArgumentException("Firstname was null!");
        if (userDto.getLastName() == null) throw new IllegalArgumentException("Lastname was null!");
        if (userDto.getEmail() == null) throw new IllegalArgumentException("Email was null!");
        if (userRepository.existsByEmail(userDto.getEmail())) throw new DataDuplicateException("Email exists!");
        if (userDto.getUsername() == null) throw new IllegalArgumentException("Username was null!");
        if (userRepository.existsByUsername(userDto.getUsername())) throw new DataDuplicateException("Username exists!");
        if (userDto.getPassword() == null) throw new IllegalArgumentException("Password was null!");
        Optional<Role> role = roleRepository.findById(userDto.getRole().getId());
        if (!role.isPresent()) throw new DataNotFoundException("Role was not found!");
        User createdEntity = userRepository.save(modelMapper.map(userDto, User.class));
        createdEntity.setRole(role.get());
        return modelMapper.map(createdEntity, UserDto.class);
    }

    @Override
    @Transactional(rollbackFor = {Exception.class})
    public UserDto update(UserDto userDto) throws DataNotFoundException, DataDuplicateException {
        if (userDto == null) throw new IllegalArgumentException("User data was null!");
        if (userDto.getId() == null || userDto.getId() == 0) throw new IllegalArgumentException("User id should not be zero or null!");
        if (!userRepository.findById(userDto.getId()).isPresent()) throw new DataNotFoundException("User id was not found!");
        if (userDto.getFirstName() == null) throw new IllegalArgumentException("Firstname was null!");
        if (userDto.getLastName() == null) throw new IllegalArgumentException("Lastname was null!");
        if (userDto.getPassword() == null) throw new IllegalArgumentException("Password was null!");
        if (userDto.getEmail() == null) throw new IllegalArgumentException("Email was null!");
        Optional<User> userWithEmail = userRepository.findByEmail(userDto.getEmail());
        if (userWithEmail.isPresent() && !userWithEmail.get().getId().equals(userDto.getId())) throw new DataDuplicateException("Email exists!");
        if (userDto.getUsername() == null) throw new IllegalArgumentException("Username was null!");
        Optional<User> userWithUsername = userRepository.findByUsername(userDto.getUsername());
        if (userWithUsername.isPresent() && !userWithUsername.get().getId().equals(userDto.getId())) throw new DataDuplicateException("Username exists!");
        if (userDto.getRole() == null) throw new IllegalArgumentException("Role was null!");
        if (!roleRepository.existsById(userDto.getRole().getId())) throw new DataNotFoundException("Role was not found!");
        User result = userRepository.save(modelMapper.map(userDto, User.class));
        return modelMapper.map(result, UserDto.class);
    }

    @Override
    @Transactional(rollbackFor = {Exception.class})
    public void deleteById(Integer id) throws DataNotFoundException {
        if (id == null) throw new IllegalArgumentException("id was null!");
        if (!userRepository.existsById(id)) throw new DataNotFoundException("Id was not found!");
        userRepository.deleteById(id);
    }

    @Override
    @Transactional(rollbackFor = {Exception.class})
    public void deleteByUsername(String username) throws DataNotFoundException {
        if (username == null) throw new IllegalArgumentException("Username was null!");
        if (!userRepository.existsByUsername(username)) throw new DataNotFoundException("Username was not found!");
        userRepository.deleteByUsername(username);
    }

    @Override
    @Transactional(rollbackFor = {Exception.class})
    public void deleteByEmail(String email) throws DataNotFoundException {
        if (email == null) throw new IllegalArgumentException("Email was null!");
        if (!userRepository.existsByEmail(email)) throw new DataNotFoundException("Email was not found!");
        userRepository.deleteByEmail(email);
    }

}
