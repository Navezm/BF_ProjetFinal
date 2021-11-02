package be.digitalcity.projetfinal.services.impl;

import be.digitalcity.projetfinal.mappers.EventCategoryMapper;
import be.digitalcity.projetfinal.models.dto.EventCategoryDTO;
import be.digitalcity.projetfinal.models.entity.EventCategory;
import be.digitalcity.projetfinal.models.form.EventCategoryForm;
import be.digitalcity.projetfinal.repository.EventCategoryRepository;
import be.digitalcity.projetfinal.services.EventCategoryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EventCategoryServiceImpl implements EventCategoryService {
    private final EventCategoryRepository repository;
    private final EventCategoryMapper mapper;

    public EventCategoryServiceImpl(EventCategoryRepository repository, EventCategoryMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public List<EventCategoryDTO> findAll() {
        return repository.findAll()
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public EventCategoryDTO getOne(Long id) {
        return repository.findById(id)
                .map(mapper::toDto)
                .orElseThrow(() -> new IllegalArgumentException("The event category doesn't exist"));
    }

    @Override
    public EventCategoryDTO delete(Long id) {
        EventCategory toDelete = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("The event category doesn't exist"));

        repository.delete(toDelete);

        return mapper.toDto(toDelete);
    }

    @Override
    public EventCategoryDTO update(Long id, EventCategoryForm eventCategoryForm) {
        EventCategory toUpdate = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("The event category doesn't exist"));

        toUpdate.setName(eventCategoryForm.getName());

        repository.save(toUpdate);

        return mapper.toDto(toUpdate);
    }

    @Override
    public EventCategoryDTO insert(EventCategoryForm eventCategoryForm) {
        EventCategory toInsert = mapper.fromFormToEntity(eventCategoryForm);

        repository.save(toInsert);

        return mapper.toDto(toInsert);
    }
}
