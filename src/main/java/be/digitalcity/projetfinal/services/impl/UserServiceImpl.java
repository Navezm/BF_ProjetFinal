package be.digitalcity.projetfinal.services.impl;

import be.digitalcity.projetfinal.mappers.AddressMapper;
import be.digitalcity.projetfinal.mappers.GroupMapper;
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
import be.digitalcity.projetfinal.services.AddressService;
import be.digitalcity.projetfinal.services.GroupService;
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
    private final AddressService addressService;
    private final GroupService groupService;
    private final GroupMapper groupMapper;

    public UserServiceImpl(UserRepository repository, UserMapper mapper, AddressMapper addressMapper, PasswordEncoder encoder, RoleService roleService, RoleMapper roleMapper, AddressService addressService, GroupService groupService, GroupMapper groupMapper) {
        this.repository = repository;
        this.mapper = mapper;
        this.addressMapper = addressMapper;
        this.encoder = encoder;
        this.roleService = roleService;
        this.roleMapper = roleMapper;
        this.addressService = addressService;
        this.groupService = groupService;
        this.groupMapper = groupMapper;
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
        toInsert.setGroup(this.groupMapper.toEntity(this.groupService.getOne(2L)));

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
    public User addRoles(User user, UserAddRoleForm userAddRoleForm) {
        List<Role> roleList = userAddRoleForm.getRoles().stream()
                        .map(this.roleService::getOne)
                        .map(this.roleMapper::toEntity)
                        .collect(Collectors.toList());

        user.addRoles(roleList);

        return user;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findUserByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("The user with username {"+username+"} couldn't be found."));
    }
}
