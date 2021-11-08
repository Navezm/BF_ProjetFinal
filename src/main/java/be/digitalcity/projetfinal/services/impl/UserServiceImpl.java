package be.digitalcity.projetfinal.services.impl;

import be.digitalcity.projetfinal.mappers.AddressMapper;
import be.digitalcity.projetfinal.mappers.RoleMapper;
import be.digitalcity.projetfinal.mappers.UserMapper;
import be.digitalcity.projetfinal.models.dto.UserDTO;
import be.digitalcity.projetfinal.models.entity.Role;
import be.digitalcity.projetfinal.models.entity.User;
import be.digitalcity.projetfinal.models.entity.abstractClass.BaseEntity;
import be.digitalcity.projetfinal.models.form.userForm.UserAddRoleForm;
import be.digitalcity.projetfinal.models.form.userForm.UserRegisterForm;
import be.digitalcity.projetfinal.models.form.userForm.UserUpdateForm;
import be.digitalcity.projetfinal.repository.UserRepository;
import be.digitalcity.projetfinal.services.RoleService;
import be.digitalcity.projetfinal.services.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository repository;
    private final UserMapper mapper;
    private final AddressMapper addressMapper;
    private final PasswordEncoder encoder;
    private final RoleService roleService;
    private final RoleMapper roleMapper;

    public UserServiceImpl(UserRepository repository, UserMapper mapper, AddressMapper addressMapper, PasswordEncoder encoder, RoleService roleService, RoleMapper roleMapper) {
        this.repository = repository;
        this.mapper = mapper;
        this.addressMapper = addressMapper;
        this.encoder = encoder;
        this.roleService = roleService;
        this.roleMapper = roleMapper;
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
        toUpdate.setPassword(encoder.encode(userUpdateForm.getPassword()));
        toUpdate.setRoles(userUpdateForm.getRoles());
        toUpdate.setGroup(userUpdateForm.getGroup());

        repository.save(toUpdate);

        return mapper.toDto(toUpdate);
    }

    @Override
    public UserDTO insert(UserRegisterForm userRegisterForm) {
        User toInsert = mapper.fromFormToEntity(userRegisterForm);

        toInsert.setAccountNonExpired(true);
        toInsert.setAccountNonLocked(true);
        toInsert.setCreditialsNonExpired(true);
        toInsert.setEnabled(true);

        toInsert.setPassword(encoder.encode(userRegisterForm.getPassword()));

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

    @Override
    public UserDTO addRoles(User user, UserAddRoleForm userAddRoleForm) {
        List<Role> roleList = userAddRoleForm.getRoles().stream()
                        .map(this.roleService::getOne)
                        .map(this.roleMapper::toEntity)
                        .collect(Collectors.toList());

        user.setRoles(roleList);

        return mapper.toDto(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findUserByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("The user with username {"+username+"} couldn't be found."));
    }
}
