package be.digitalcity.projetfinal.mappers;

import be.digitalcity.projetfinal.models.dto.GroupDTO;
import be.digitalcity.projetfinal.models.entity.Group;
import be.digitalcity.projetfinal.models.form.GroupForm;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface GroupMapper {
    Group toEntity(GroupDTO groupDTO);

    GroupDTO toDto(Group group);

    Group fromFormToEntity(GroupForm groupForm);
}
