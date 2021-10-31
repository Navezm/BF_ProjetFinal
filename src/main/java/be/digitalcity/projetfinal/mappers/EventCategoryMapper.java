package be.digitalcity.projetfinal.mappers;

import be.digitalcity.projetfinal.models.dto.EventCategoryDTO;
import be.digitalcity.projetfinal.models.entity.EventCategory;
import be.digitalcity.projetfinal.models.form.EventCategoryForm;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EventCategoryMapper {
    EventCategory toEntity(EventCategoryDTO eventCategoryDTO);

    EventCategoryDTO toDto(EventCategory eventCategory);

    EventCategory fromFormToEntity(EventCategoryForm eventCategoryForm);
}
