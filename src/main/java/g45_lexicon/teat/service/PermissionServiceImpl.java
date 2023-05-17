package g45_lexicon.teat.service;

import g45_lexicon.teat.exception.DataDuplicateException;
import g45_lexicon.teat.exception.DataNotFoundException;
import g45_lexicon.teat.model.dto.PermissionDto;
import g45_lexicon.teat.model.entity.Permission;
import g45_lexicon.teat.repository.PermissionRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PermissionServiceImpl implements PermissionService {
    @Autowired
    PermissionRepository permissionRepository;
    @Autowired
    ModelMapper modelMapper;

    @Override
    public List<PermissionDto> getAll() {
        List<Permission> list = new ArrayList<>();
        permissionRepository.findAll().iterator().forEachRemaining(list::add);
        return list.stream().map(category -> modelMapper.map(category, PermissionDto.class)).collect(Collectors.toList());
    }


    @Override
    public PermissionDto findById(Integer id) throws DataNotFoundException {
        if (id == null) throw new IllegalArgumentException("Permission id was null!");
        Optional<Permission> optionalPermission = permissionRepository.findById(id);
        if (!optionalPermission.isPresent()) throw new DataNotFoundException("Permission id was not valid!");
        Permission permission = optionalPermission.get();
        return modelMapper.map(permission, PermissionDto.class);
    }

    @Override
    public PermissionDto findByName(String name) throws DataNotFoundException {
        if (name == null) throw new IllegalArgumentException("Permission name was null!");
        Optional<Permission> optionalPermission = permissionRepository.findByPermissionName(name);
        if (!optionalPermission.isPresent()) throw new DataNotFoundException("Permission name was not valid!");
        Permission permission = optionalPermission.get();
        return modelMapper.map(permission, PermissionDto.class);
    }

    @Override
    @Transactional(rollbackFor = {Exception.class})
    public PermissionDto create(PermissionDto permissionDto) throws DataDuplicateException {
        if (permissionDto == null) throw new IllegalArgumentException("Permission was null!");
        if (permissionDto.getId() != null) throw new IllegalArgumentException("Permission id should be automatically generated!");
        Permission createdEntity = permissionRepository.save(modelMapper.map(permissionDto, Permission.class));
        return modelMapper.map(createdEntity, PermissionDto.class);
    }

    @Override
    @Transactional(rollbackFor = {Exception.class})
    public PermissionDto update(PermissionDto permissionDto) throws DataNotFoundException {
        if (permissionDto == null) throw new IllegalArgumentException("Permission data was null!");
        if (permissionDto.getId() == null || permissionDto.getId() == 0) throw new IllegalArgumentException("Permission id should not be null or zero!");
        if (!permissionRepository.findById(permissionDto.getId()).isPresent()) throw new DataNotFoundException("Permission id was not found!");
        Permission result = permissionRepository.save(modelMapper.map(permissionDto, Permission.class));
        return modelMapper.map(result, PermissionDto.class);
    }

    @Override
    public void delete(Integer id) throws DataNotFoundException {
        if (id == null) throw new IllegalArgumentException("id was null!");
        if (!permissionRepository.existsById(id)) throw new DataNotFoundException("Id was not valid!");
        permissionRepository.deleteById(id);
    }
}
