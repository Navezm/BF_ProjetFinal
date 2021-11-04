package be.digitalcity.projetfinal.mappers;

import be.digitalcity.projetfinal.models.dto.EventCategoryDTO;
import be.digitalcity.projetfinal.models.entity.EventCategory;
import be.digitalcity.projetfinal.models.form.EventCategoryForm;
import org.springframework.stereotype.Service;

@Service
public class EventCategoryMapper implements BaseMapper<EventCategoryDTO, EventCategoryForm, EventCategory> {
    @Override
    public EventCategory toEntity(EventCategoryDTO dto) {
        if (dto == null) return null;

        return new EventCategory(
                dto.getName()
        );
    }

    @Override
    public EventCategoryDTO toDto(EventCategory entity) {
        if (entity == null) return null;

        EventCategoryDTO eventCategoryDTO = new EventCategoryDTO();

        eventCategoryDTO.setName(entity.getName());
        eventCategoryDTO.setId(entity.getId());

        return eventCategoryDTO;
    }

    @Override
    public EventCategory fromFormToEntity(EventCategoryForm form) {
        if (form == null) return null;

        return new EventCategory(
                form.getName()
        );
    }
}
