package be.digitalcity.projetfinal.services.impl;

import be.digitalcity.projetfinal.mappers.PaintingTypeMapper;
import be.digitalcity.projetfinal.models.dto.PaintingTypeDTO;
import be.digitalcity.projetfinal.models.entity.PaintingType;
import be.digitalcity.projetfinal.models.form.PaintingTypeForm;
import be.digitalcity.projetfinal.repository.PaintingTypeRepository;
import be.digitalcity.projetfinal.services.PaintingTypeService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PaintingTypeServiceImpl implements PaintingTypeService {
    private final PaintingTypeRepository repository;
    private final PaintingTypeMapper mapper;

    public PaintingTypeServiceImpl(PaintingTypeRepository repository, PaintingTypeMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public List<PaintingTypeDTO> findAll() {
        return repository.findAll()
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public PaintingTypeDTO getOne(Long id) {
        return repository.findById(id)
                .map(mapper::toDto)
                .orElseThrow(() -> new IllegalArgumentException("The painting type doesn't exist"));
    }

    @Override
    public PaintingTypeDTO delete(Long id) {
        PaintingType toDelete = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("The painting type doesn't exist"));

        repository.delete(toDelete);

        return mapper.toDto(toDelete);
    }

    @Override
    public PaintingTypeDTO update(Long id, PaintingTypeForm paintingTypeForm) {
        PaintingType toUpdate = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("The painting type doesn't exist"));

        toUpdate.setName(paintingTypeForm.getName());

        repository.save(toUpdate);

        return mapper.toDto(toUpdate);
    }

    @Override
    public PaintingTypeDTO insert(PaintingTypeForm paintingTypeForm) {
        PaintingType toInsert = mapper.fromFormToEntity(paintingTypeForm);

        repository.save(toInsert);

        return mapper.toDto(toInsert);
    }
}
