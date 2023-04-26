package g45_lexicon.teat.service;

import g45_lexicon.teat.exception.DataDuplicateException;
import g45_lexicon.teat.exception.DataNotFoundException;
import g45_lexicon.teat.model.dto.RoleDto;
import g45_lexicon.teat.model.entity.Role;
import g45_lexicon.teat.repository.RoleRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    ModelMapper modelMapper;

    @Override
    public List<RoleDto> getAll() {
        List<Role> roleList = roleRepository.findAllByOrOrderByIdDesc();
        return modelMapper.map(roleList, new TypeToken<List<RoleDto>>() {
        }.getType());
    }

    @Override
    public RoleDto findById(Integer id) {
        if (id == null) throw new IllegalArgumentException("Role id was null");
        Optional<Role> optionalRole = roleRepository.findById(id);
        if (!optionalRole.isPresent()) throw new DataNotFoundException("Role id was not valid");
        Role role = optionalRole.get();
        return modelMapper.map(role, RoleDto.class);
    }

    @Override
    public RoleDto create(RoleDto roleDto) {
        if (roleDto == null) throw new IllegalArgumentException("Role was null");
        if (roleDto.getId() != 0) throw new IllegalArgumentException("Role id should be null or zero");
        if (roleRepository.existsById(roleDto.getId())) throw new DataDuplicateException("Role already existed");
        Role createdEntity = roleRepository.save(modelMapper.map(roleDto, Role.class));
        return modelMapper.map(createdEntity, RoleDto.class);
    }

    @Override
    public void update(RoleDto roleDto) {
        if (roleDto == null) throw new IllegalArgumentException("Role data was null");
        if (roleDto.getId() == 0) throw new IllegalArgumentException("Role id should not be zero");
        if (!roleRepository.findById(roleDto.getId()).isPresent())
            throw new DataNotFoundException("Data not found error");
        if (roleRepository.findByRoleTitle(roleDto.getRoleTitle()).isPresent())
            throw new DataDuplicateException("Duplicate event name error"); roleRepository.save(modelMapper.map(roleDto, Role.class));
    }

    @Override
    public void delete(Integer id) {
        RoleDto roleDto = findById(id);
        if (roleDto == null) throw new DataNotFoundException("Id was not valid");
        roleRepository.deleteById(id);
    }
}
