package be.digitalcity.projetfinal.services.impl;

import be.digitalcity.projetfinal.mappers.EventCategoryMapper;
import be.digitalcity.projetfinal.mappers.PictureMapper;
import be.digitalcity.projetfinal.models.dto.PictureDTO;
import be.digitalcity.projetfinal.models.entity.Picture;
import be.digitalcity.projetfinal.models.entity.abstractClass.BaseEntity;
import be.digitalcity.projetfinal.models.form.PictureForm;
import be.digitalcity.projetfinal.repository.PictureRepository;
import be.digitalcity.projetfinal.services.EventCategoryService;
import be.digitalcity.projetfinal.services.PictureService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PictureServiceImpl implements PictureService {
    private final PictureRepository repository;
    private final PictureMapper mapper;
    private final EventCategoryService eventCategoryService;
    private final EventCategoryMapper eventCategoryMapper;

    public PictureServiceImpl(PictureRepository repository, PictureMapper mapper, EventCategoryService eventCategoryService, EventCategoryMapper eventCategoryMapper) {
        this.repository = repository;
        this.mapper = mapper;
        this.eventCategoryService = eventCategoryService;
        this.eventCategoryMapper = eventCategoryMapper;
    }

    @Override
    public List<PictureDTO> findAll() {
        return repository.findAll()
                .stream()
                .filter(BaseEntity::isActive)
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public PictureDTO getOne(Long id) {
        return repository.findById(id)
                .filter(BaseEntity::isActive)
                .map(mapper::toDto)
                .orElseThrow(() -> new IllegalArgumentException("The picture doesn't exist"));
    }

    @Override
    public PictureDTO delete(Long id) {
        Picture toDelete = repository.findById(id)
                .filter(BaseEntity::isActive)
                .orElseThrow(() -> new IllegalArgumentException("The picture doesn't exist"));

        toDelete.setActive(false);

        repository.save(toDelete);

        return mapper.toDto(toDelete);
    }

    @Override
    public PictureDTO update(Long id, PictureForm pictureForm) {
        Picture toUpdate = repository.findById(id)
                .filter(BaseEntity::isActive)
                .orElseThrow(() -> new IllegalArgumentException("The picture doesn't exist"));

        toUpdate.setName(pictureForm.getName());
        toUpdate.setPrice(pictureForm.getPrice());
        toUpdate.setDescription(pictureForm.getDescription());
        toUpdate.setEventCategory(this.eventCategoryMapper.toEntity(this.eventCategoryService.getOne(pictureForm.getEventCategoryId())));
        if(pictureForm.getSrc() != null)
            toUpdate.setSrc(pictureForm.getSrc());
        if(pictureForm.getIsAvailable() != null)
            toUpdate.setAvailable(pictureForm.getIsAvailable());

        repository.save(toUpdate);

        return mapper.toDto(toUpdate);
    }

//    @Override
//    public PictureDTO insert(PictureForm pictureForm, String filename) {
//        pictureForm.setSrc(filename);
//        Picture toInsert = mapper.fromFormToEntity(pictureForm);
//
//        repository.save(toInsert);
//
//        return mapper.toDto(toInsert);
//    }

    @Override
    public PictureDTO insert(PictureForm pictureForm) {
//        pictureForm.setSrc(filename);
        Picture toInsert = mapper.fromFormToEntity(pictureForm);

        repository.save(toInsert);

        return mapper.toDto(toInsert);
    }

    @Override
    public List<PictureDTO> findByType(Long id) {
        this.eventCategoryService.getOne(id);

        return repository.findByType(id)
                .stream()
                .filter(BaseEntity::isActive)
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<PictureDTO> findByAvailability() {
        return repository.findByAvailibility()
                .stream()
                .filter(BaseEntity::isActive)
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }
}
