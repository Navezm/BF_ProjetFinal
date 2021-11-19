package be.digitalcity.projetfinal.services.impl;

import be.digitalcity.projetfinal.mappers.PaintingMapper;
import be.digitalcity.projetfinal.mappers.PaintingTypeMapper;
import be.digitalcity.projetfinal.models.dto.PaintingDTO;
import be.digitalcity.projetfinal.models.entity.Painting;
import be.digitalcity.projetfinal.models.entity.abstractClass.BaseEntity;
import be.digitalcity.projetfinal.models.form.PaintingForm;
import be.digitalcity.projetfinal.repository.PaintingRepository;
import be.digitalcity.projetfinal.services.PaintingService;
import be.digitalcity.projetfinal.services.PaintingTypeService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PaintingServiceImpl implements PaintingService {
    private final PaintingRepository repository;
    private final PaintingMapper mapper;
    private final PaintingTypeService paintingTypeService;
    private final PaintingTypeMapper paintingTypeMapper;

    public PaintingServiceImpl(PaintingRepository repository, PaintingMapper mapper, PaintingTypeService paintingTypeService, PaintingTypeMapper paintingTypeMapper) {
        this.repository = repository;
        this.mapper = mapper;
        this.paintingTypeService = paintingTypeService;
        this.paintingTypeMapper = paintingTypeMapper;
    }

    @Override
    public List<PaintingDTO> findAll() {
        return repository.findAll()
                .stream()
                .filter(BaseEntity::isActive)
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public PaintingDTO getOne(Long id) {
        return repository.findById(id)
                .filter(BaseEntity::isActive)
                .map(mapper::toDto)
                .orElseThrow(() -> new IllegalArgumentException("The painting doesn't exist"));
    }

    @Override
    public PaintingDTO delete(Long id) {
        Painting toDelete = repository.findById(id)
                .filter(BaseEntity::isActive)
                .orElseThrow(() -> new IllegalArgumentException("The painting doesn't exist"));

        toDelete.setActive(false);

        repository.save(toDelete);

        return mapper.toDto(toDelete);
    }

    @Override
    public PaintingDTO update(Long id, PaintingForm paintingForm) {
        Painting toUpdate = repository.findById(id)
                .filter(BaseEntity::isActive)
                .orElseThrow(() -> new IllegalArgumentException("The painting doesn't exist"));

        toUpdate.setPrice(paintingForm.getPrice());
        toUpdate.setDescription(paintingForm.getDescription());
        toUpdate.setName(paintingForm.getName());
        toUpdate.setPaintingType(this.paintingTypeMapper.toEntity(this.paintingTypeService.getOne(paintingForm.getPaintingTypeId())));
        if (paintingForm.getSrc() != null) {
            toUpdate.setSrc(paintingForm.getSrc());
        }
        if (paintingForm.getIsAvailable() != null) {
            toUpdate.setAvailable(paintingForm.getIsAvailable());
        }

        repository.save(toUpdate);

        return mapper.toDto(toUpdate);
    }

    @Override
    public PaintingDTO insert(PaintingForm paintingForm) {
        Painting toInsert = mapper.fromFormToEntity(paintingForm);

        repository.save(toInsert);

        return mapper.toDto(toInsert);
    }

    @Override
    public List<PaintingDTO> findByType(Long id) {
        this.paintingTypeService.getOne(id);

        return repository.findByType(id)
                .stream()
                .filter(BaseEntity::isActive)
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<PaintingDTO> findByAvailability() {
        return repository.findByAvailibility()
                .stream()
                .filter(BaseEntity::isActive)
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }
}
