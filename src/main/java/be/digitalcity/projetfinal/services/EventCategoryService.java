package be.digitalcity.projetfinal.services;

import be.digitalcity.projetfinal.models.dto.EventCategoryDTO;
import be.digitalcity.projetfinal.models.form.EventCategoryForm;

import java.util.List;

public interface EventCategoryService {
    List<EventCategoryDTO> findAll();

    EventCategoryDTO getOne(Long id);

    EventCategoryDTO delete(Long id);

    EventCategoryDTO update(Long id, EventCategoryForm eventCategoryForm);

    EventCategoryDTO insert(EventCategoryForm eventCategoryForm);
}
