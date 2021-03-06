package be.digitalcity.projetfinal.mappers;

import be.digitalcity.projetfinal.models.dto.RoleDTO;
import be.digitalcity.projetfinal.models.entity.Role;
import be.digitalcity.projetfinal.models.form.RoleForm;
import org.springframework.stereotype.Service;

@Service
public class RoleMapper implements BaseMapper<RoleDTO, RoleForm, Role>{
    @Override
    public Role toEntity(RoleDTO dto) {
        if (dto == null) return null;

        Role role = new Role(
                dto.getName()
        );
        role.setId(dto.getId());

        return role;
    }

    @Override
    public RoleDTO toDto(Role entity) {
        if (entity == null) return null;

        RoleDTO roleDTO = new RoleDTO();

        roleDTO.setName(entity.getName());
        roleDTO.setId(entity.getId());

        return roleDTO;
    }

    @Override
    public Role fromFormToEntity(RoleForm form) {
        if (form == null) return null;

        return new Role(
                form.getName()
        );
    }
}
