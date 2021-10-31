package be.digitalcity.projetfinal.mappers;

import be.digitalcity.projetfinal.models.dto.UserDTO;
import be.digitalcity.projetfinal.models.entity.User;
import be.digitalcity.projetfinal.models.form.userForm.UserRegisterForm;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class UserMapper implements BaseMapper<UserDTO, UserRegisterForm, User> {
    private final AddressMapper addressMapper;
    private final RoleMapper roleMapper;
    private final GroupMapper groupMapper;

    public UserMapper(AddressMapper addressMapper, RoleMapper roleMapper, GroupMapper groupMapper) {
        this.addressMapper = addressMapper;
        this.roleMapper = roleMapper;
        this.groupMapper = groupMapper;
    }

    @Override
    public User toEntity(UserDTO dto) {
        if (dto == null) return null;

        User user = new User();

        user.setAddress(addressMapper.toEntity(dto.getAddress()));
        user.setEmail(dto.getEmail());
        user.setUsername(dto.getUsername());
        user.setGroup(groupMapper.toEntity(dto.getGroup()));
        user.setRoles(dto.getRoles().stream()
                .map(roleMapper::toEntity)
                .collect(Collectors.toList()));

        return user;
    }

    @Override
    public UserDTO toDto(User entity) {
        if (entity == null) return null;

        UserDTO userDTO = new UserDTO();

        userDTO.setUsername(entity.getUsername());
        userDTO.setEmail(entity.getEmail());
        userDTO.setAddress(addressMapper.toDto(entity.getAddress()));
        userDTO.setGroup(groupMapper.toDto(entity.getGroup()));
        userDTO.setRoles(entity.getRoles().stream()
                .map(roleMapper::toDto)
                .collect(Collectors.toList()));

        return userDTO;
    }

    @Override
    public User fromFormToEntity(UserRegisterForm form) {
        if (form == null) return null;

        User user = new User();

        user.setPassword(form.getPassword());
        user.setUsername(form.getUsername());
        user.setEmail(form.getEmail());
        user.setAddress(addressMapper.fromFormToEntity(form.getAddress()));

        return user;
    }
}
