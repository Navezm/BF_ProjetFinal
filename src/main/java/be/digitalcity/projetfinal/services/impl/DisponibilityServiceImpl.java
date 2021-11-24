package be.digitalcity.projetfinal.services.impl;

import be.digitalcity.projetfinal.mappers.DisponibilityMapper;
import be.digitalcity.projetfinal.models.dto.DisponibilityDTO;
import be.digitalcity.projetfinal.models.entity.Disponibility;
import be.digitalcity.projetfinal.models.entity.abstractClass.BaseEntity;
import be.digitalcity.projetfinal.models.form.DisponibilityForm;
import be.digitalcity.projetfinal.repository.DisponibilityRepository;
import be.digitalcity.projetfinal.services.DisponibilityService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DisponibilityServiceImpl implements DisponibilityService {
    private final DisponibilityRepository repository;
    private final DisponibilityMapper mapper;

    public DisponibilityServiceImpl(DisponibilityRepository repository, DisponibilityMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public List<DisponibilityDTO> findAll() {
        return repository.findAll()
                .stream()
                .filter(BaseEntity::isActive)
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public DisponibilityDTO getOne(Long id) {
        return repository.findById(id)
                .filter(BaseEntity::isActive)
                .map(mapper::toDto)
                .orElseThrow(() -> new IllegalArgumentException("The disponibility doesn't exist"));
    }

    @Override
    public DisponibilityDTO delete(Long id) {
        Disponibility toDelete = repository.findById(id)
                .filter(BaseEntity::isActive)
                .orElseThrow(() -> new IllegalArgumentException("The disponibility doesn't exist"));

        toDelete.setActive(false);

        repository.save(toDelete);

        return mapper.toDto(toDelete);
    }

    @Override
    public DisponibilityDTO update(Long id, DisponibilityForm disponibilityForm) {
        Disponibility toUpdate = repository.findById(id)
                .filter(BaseEntity::isActive)
                .orElseThrow(() -> new IllegalArgumentException("The disponibility doesn't exist"));

        toUpdate.setStatus(disponibilityForm.isStatus());
        toUpdate.setDate(disponibilityForm.getDate());

        repository.save(toUpdate);

        return mapper.toDto(toUpdate);
    }

    @Override
    public DisponibilityDTO insert(DisponibilityForm disponibilityForm) {
        Disponibility toInsert = mapper.fromFormToEntity(disponibilityForm);

        repository.save(toInsert);

        return mapper.toDto(toInsert);
    }
}
