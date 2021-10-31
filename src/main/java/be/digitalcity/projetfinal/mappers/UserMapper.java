package be.digitalcity.projetfinal.mappers;

import be.digitalcity.projetfinal.models.dto.UserDTO;
import be.digitalcity.projetfinal.models.entity.User;
import be.digitalcity.projetfinal.models.form.userForm.UserRegisterForm;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
public interface UserMapper {
//    @Mapping(target = "roleId", source = "role.id")
//    UserDTO mapFromEntity(User entity);
//    @InheritInverseConfiguration
//    User mapFromDto(UserDTO dto);
    User toEntity(UserDTO userDTO);

    UserDTO toDto(User user);

    User fromFormToEntity(UserRegisterForm userRegisterForm);
}
