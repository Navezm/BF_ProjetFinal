package be.digitalcity.projetfinal.services.impl;

import be.digitalcity.projetfinal.mappers.PicturePurchaseMapper;
import be.digitalcity.projetfinal.mappers.UtilMapper;
import be.digitalcity.projetfinal.models.dto.PicturePurchaseDTO;
import be.digitalcity.projetfinal.models.entity.PicturePurchase;
import be.digitalcity.projetfinal.models.form.PicturePurchaseForm;
import be.digitalcity.projetfinal.models.form.typeForm.DateForm;
import be.digitalcity.projetfinal.models.form.typeForm.StatusForm;
import be.digitalcity.projetfinal.repository.PicturePurchaseRepository;
import be.digitalcity.projetfinal.services.PicturePurchaseService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PicturePurchaseServiceImpl implements PicturePurchaseService {
    private final PicturePurchaseRepository repository;
    private final PicturePurchaseMapper mapper;
    private final UtilMapper utilMapper;

    public PicturePurchaseServiceImpl(PicturePurchaseRepository repository, PicturePurchaseMapper mapper, UtilMapper utilMapper) {
        this.repository = repository;
        this.mapper = mapper;
        this.utilMapper = utilMapper;
    }

    @Override
    public List<PicturePurchaseDTO> findAll() {
        return repository.findAll()
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public PicturePurchaseDTO getOne(Long id) {
        return repository.findById(id)
                .map(mapper::toDto)
                .orElseThrow(() -> new IllegalArgumentException("The picture purchase doesn't exist"));
    }

    @Override
    public PicturePurchaseDTO delete(Long id) {
        PicturePurchase toDelete = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("The picture purchase doesn't exist"));

        toDelete.setActive(false);

        repository.save(toDelete);

        return mapper.toDto(toDelete);
    }

    @Override
    public PicturePurchaseDTO update(Long id, PicturePurchaseForm picturePurchaseForm) {
        PicturePurchase toUpdate = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("The picture purchase doesn't exist"));

        toUpdate.setPictures(picturePurchaseForm.getPictures());
        toUpdate.setUser(picturePurchaseForm.getUser());
        toUpdate.setStatus(picturePurchaseForm.getStatus());

        repository.save(toUpdate);

        return mapper.toDto(toUpdate);
    }

    @Override
    public PicturePurchaseDTO insert(PicturePurchaseForm picturePurchaseForm) {
        PicturePurchase toInsert = mapper.fromFormToEntity(picturePurchaseForm);

        repository.save(toInsert);

        return mapper.toDto(toInsert);
    }

    @Override
    public List<PicturePurchaseDTO> findByUser(Long id) {
        return repository.findPaintingPurchasesByUserId(id)
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<PicturePurchaseDTO> findByStatus(StatusForm status) {
        return repository.findPaintingPurchasesByStatus(utilMapper.fromStatusFormToStatus(status))
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<PicturePurchaseDTO> findByOrderDate(DateForm date) {
        return repository.findPaintingPurchasesByCreatedAt(utilMapper.fromDateFormToDate(date))
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }
}
