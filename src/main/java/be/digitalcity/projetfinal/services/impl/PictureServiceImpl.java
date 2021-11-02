package be.digitalcity.projetfinal.services.impl;

import be.digitalcity.projetfinal.mappers.PictureMapper;
import be.digitalcity.projetfinal.models.dto.PaintingDTO;
import be.digitalcity.projetfinal.models.dto.PictureDTO;
import be.digitalcity.projetfinal.models.entity.Picture;
import be.digitalcity.projetfinal.models.form.PictureForm;
import be.digitalcity.projetfinal.repository.PictureRepository;
import be.digitalcity.projetfinal.services.PictureService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PictureServiceImpl implements PictureService {
    private final PictureRepository repository;
    private final PictureMapper mapper;

    public PictureServiceImpl(PictureRepository repository, PictureMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public List<PictureDTO> findAll() {
        return repository.findAll()
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public PictureDTO getOne(Long id) {
        return repository.findById(id)
                .map(mapper::toDto)
                .orElseThrow(() -> new IllegalArgumentException("The picture doesn't exist"));
    }

    @Override
    public PictureDTO delete(Long id) {
        Picture toDelete = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("The picture doesn't exist"));

        repository.delete(toDelete);

        return mapper.toDto(toDelete);
    }

    @Override
    public PictureDTO update(Long id, PictureForm pictureForm) {
        Picture toUpdate = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("The picture doesn't exist"));

        toUpdate.setAvailable(pictureForm.isAvailable());
        toUpdate.setName(pictureForm.getName());
        toUpdate.setPrice(pictureForm.getPrice());
        toUpdate.setDescription(pictureForm.getDescription());
        toUpdate.setEventCategory(pictureForm.getEventCategory());
        toUpdate.setSrc(pictureForm.getSrc());

        repository.save(toUpdate);

        return mapper.toDto(toUpdate);
    }

    @Override
    public PictureDTO insert(PictureForm pictureForm) {
        Picture toInsert = mapper.fromFormToEntity(pictureForm);

        repository.save(toInsert);

        return mapper.toDto(toInsert);
    }
}
