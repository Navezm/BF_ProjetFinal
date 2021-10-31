package be.digitalcity.projetfinal.mappers;

import be.digitalcity.projetfinal.models.dto.RoleDTO;
import be.digitalcity.projetfinal.models.entity.Role;
import be.digitalcity.projetfinal.models.form.RoleForm;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    Role toEntity(RoleDTO roleDTO);

    RoleDTO toDto(Role role);

    Role fromFormToEntity(RoleForm roleForm);
}
