package g45_lexicon.teat.service;

import g45_lexicon.teat.exception.DataDuplicateException;
import g45_lexicon.teat.exception.DataNotFoundException;
import g45_lexicon.teat.model.dto.PermissionDto;
import g45_lexicon.teat.model.dto.RoleDto;
import g45_lexicon.teat.model.dto.UserDto;
import g45_lexicon.teat.model.entity.Permission;
import g45_lexicon.teat.model.entity.Role;
import g45_lexicon.teat.model.entity.User;
import g45_lexicon.teat.repository.RoleRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    ModelMapper modelMapper;

    @Override
    public List<RoleDto> getAll() {
        List<Role> list = new ArrayList<>();
        roleRepository.findAll().iterator().forEachRemaining(list::add);
        return list.stream().map(category -> modelMapper.map(category, RoleDto.class)).collect(Collectors.toList());
    }

    @Override
    public RoleDto findById(Integer id) throws DataNotFoundException {
        if (id == null) throw new IllegalArgumentException("Role id was null!");
        Optional<Role> optionalRole = roleRepository.findById(id);
        if (!optionalRole.isPresent()) throw new DataNotFoundException("Role id was not valid!");
        Role role = optionalRole.get();
        return modelMapper.map(role, RoleDto.class);
    }

    @Override
    public RoleDto findByTitle(String title) throws DataNotFoundException {
        if (title == null) throw new IllegalArgumentException("Role title was null!");
        Optional<Role> optionalRole = roleRepository.findByRoleTitle(title);
        if (!optionalRole.isPresent()) throw new DataNotFoundException("Role title was not valid!");
        Role role = optionalRole.get();
        return modelMapper.map(role, RoleDto.class);
    }


    @Override
    @Transactional(rollbackFor = {Exception.class})
    public RoleDto create(RoleDto roleDto) throws DataDuplicateException {
        if (roleDto == null) throw new IllegalArgumentException("Role was null!");
        if (roleDto.getId() != null) throw new IllegalArgumentException("Role id should be automatically generated!");
        Role createdEntity = roleRepository.save(modelMapper.map(roleDto, Role.class));
        return modelMapper.map(createdEntity, RoleDto.class);
    }

    @Override
    @Transactional(rollbackFor = {Exception.class})
    public RoleDto update(RoleDto roleDto) throws DataNotFoundException, DataDuplicateException {
        if (roleDto == null) throw new IllegalArgumentException("Role data was null!");
        if (roleDto.getId() == null) throw new IllegalArgumentException("Role id need to be indicated!");
        if (!roleRepository.findById(roleDto.getId()).isPresent()) throw new DataNotFoundException("Role id was not found!");
        Role result = roleRepository.save(modelMapper.map(roleDto, Role.class));
        return modelMapper.map(result, RoleDto.class);
    }

    @Override
    public void delete(Integer id) throws DataNotFoundException {
        if (id == null) throw new IllegalArgumentException("id was null!");
        if (!roleRepository.existsById(id)) throw new DataNotFoundException("Id was not valid!");
        roleRepository.deleteById(id);
    }
}
