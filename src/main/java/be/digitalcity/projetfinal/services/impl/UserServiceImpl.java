package be.digitalcity.projetfinal.services.impl;

import be.digitalcity.projetfinal.mappers.AddressMapper;
import be.digitalcity.projetfinal.mappers.UserMapper;
import be.digitalcity.projetfinal.models.dto.UserDTO;
import be.digitalcity.projetfinal.models.entity.User;
import be.digitalcity.projetfinal.models.entity.abstractClass.BaseEntity;
import be.digitalcity.projetfinal.models.form.userForm.UserInsertForm;
import be.digitalcity.projetfinal.models.form.userForm.UserRegisterForm;
import be.digitalcity.projetfinal.models.form.userForm.UserUpdateForm;
import be.digitalcity.projetfinal.repository.UserRepository;
import be.digitalcity.projetfinal.services.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository repository;
    private final UserMapper mapper;
    private final AddressMapper addressMapper;

    public UserServiceImpl(UserRepository repository, UserMapper mapper, AddressMapper addressMapper) {
        this.repository = repository;
        this.mapper = mapper;
        this.addressMapper = addressMapper;
    }

    @Override
    public List<UserDTO> findAll() {
        return repository.findAll()
                .stream()
                .filter(BaseEntity::isActive)
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public UserDTO getOne(Long id) {
        return repository.findById(id)
                .filter(BaseEntity::isActive)
                .map(mapper::toDto)
                .orElseThrow(() -> new IllegalArgumentException("The user doesn't exist"));
    }

    @Override
    public UserDTO delete(Long id) {
        User toDelete = repository.findById(id)
                .filter(BaseEntity::isActive)
                .orElseThrow(() -> new IllegalArgumentException("The user doesn't exist"));

        toDelete.setActive(false);

        repository.save(toDelete);

        return mapper.toDto(toDelete);
    }

    @Override
    public UserDTO update(Long id, UserUpdateForm userUpdateForm) {
        User toUpdate = repository.findById(id)
                .filter(BaseEntity::isActive)
                .orElseThrow(() -> new IllegalArgumentException("The user doesn't exist"));

        toUpdate.setAddress(addressMapper.fromFormToEntity(userUpdateForm.getAddress()));
        toUpdate.setUsername(userUpdateForm.getUsername());
        toUpdate.setEmail(userUpdateForm.getEmail());
        toUpdate.setPassword(userUpdateForm.getPassword());
        toUpdate.setRoles(userUpdateForm.getRoles());
        toUpdate.setGroup(userUpdateForm.getGroup());

        repository.save(toUpdate);

        return mapper.toDto(toUpdate);
    }

    @Override
    public UserDTO insert(UserRegisterForm userRegisterForm) {
        User toInsert = mapper.fromFormToEntity(userRegisterForm);

        repository.save(toInsert);

        return mapper.toDto(toInsert);
    }

    @Override
    public List<UserDTO> findByGroup(Long id) {
        return repository.findUsersByGroupId(id)
                .stream()
                .filter(BaseEntity::isActive)
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }
}
