package g45_lexicon.teat.service;

import g45_lexicon.teat.exception.DataDuplicateException;
import g45_lexicon.teat.exception.DataNotFoundException;
import g45_lexicon.teat.model.dto.PermissionDto;
import g45_lexicon.teat.model.entity.Permission;
import g45_lexicon.teat.repository.PermissionRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PermissionServiceImpl implements PermissionService{
    @Autowired
    PermissionRepository permissionRepository;
    @Autowired
    ModelMapper modelMapper;

    @Override
    public List<PermissionDto> getAll() {
        List<Permission> permissionList = permissionRepository.findAllByOrOrderByIdDesc();
        return modelMapper.map(permissionList, new TypeToken<List<PermissionDto>>() {
        }.getType());
    }

    @Override
    public PermissionDto findById(Integer id) {
        if (id == null) throw new IllegalArgumentException("Permission id was null");
        Optional<Permission> optionalPermission = permissionRepository.findById(id);
        if (!optionalPermission.isPresent()) throw new DataNotFoundException("Permission id was not valid");
        Permission permission = optionalPermission.get();
        return modelMapper.map(permission, PermissionDto.class);
    }

    @Override
    public PermissionDto create(PermissionDto permissionDto) {
        if (permissionDto == null) throw new IllegalArgumentException("Permission was null");
        if (permissionDto.getId() != 0) throw new IllegalArgumentException("Permission id should be null or zero");
        if (permissionRepository.existsById(permissionDto.getId())) throw new DataDuplicateException("Permission already existed");
        Permission createdEntity = permissionRepository.save(modelMapper.map(permissionDto, Permission.class));
        return modelMapper.map(createdEntity, PermissionDto.class);
    }

    @Override
    public void update(PermissionDto permissionDto) {
        if (permissionDto == null) throw new IllegalArgumentException("Permission data was null");
        if (permissionDto.getId() == 0) throw new IllegalArgumentException("Permission id should not be zero");
        if (!permissionRepository.findById(permissionDto.getId()).isPresent())
            throw new DataNotFoundException("Data not found error");
        permissionRepository.save(modelMapper.map(permissionDto, Permission.class));
    }

    @Override
    public void delete(Integer id) {
        PermissionDto permissionDto = findById(id);
        if (permissionDto == null) throw new DataNotFoundException("Id was not valid");
        permissionRepository.deleteById(id);
    }
}
