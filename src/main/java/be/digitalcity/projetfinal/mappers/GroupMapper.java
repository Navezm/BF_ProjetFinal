package be.digitalcity.projetfinal.mappers;

import be.digitalcity.projetfinal.models.dto.GroupDTO;
import be.digitalcity.projetfinal.models.entity.Group;
import be.digitalcity.projetfinal.models.form.GroupForm;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class GroupMapper implements BaseMapper<GroupDTO, GroupForm, Group> {
    private final RoleMapper roleMapper;

    public GroupMapper(RoleMapper roleMapper) {
        this.roleMapper = roleMapper;
    }

    @Override
    public Group toEntity(GroupDTO dto) {
        if (dto == null) return null;

        Group group = new Group(
                dto.getName(),
                dto.getRoleList()
                        .stream()
                        .map(roleMapper::toEntity)
                        .collect(Collectors.toSet())
        );
        group.setId(dto.getId());

        return group;
    }

    @Override
    public GroupDTO toDto(Group entity) {
        if (entity == null) return null;

        GroupDTO groupDTO = new GroupDTO();

        groupDTO.setName(entity.getName());
        groupDTO.setRoleList(entity.getRoleList()
                .stream()
                .map(roleMapper::toDto)
                .collect(Collectors.toSet()));
        groupDTO.setId(entity.getId());

        return groupDTO;
    }

    @Override
    public Group fromFormToEntity(GroupForm form) {
        if (form == null) return null;

        return new Group(
                form.getName(),
                form.getRoleList()
        );
    }
}
