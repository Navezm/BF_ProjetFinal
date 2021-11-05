package be.digitalcity.projetfinal.services.impl;

import be.digitalcity.projetfinal.mappers.PaintingPurchaseMapper;
import be.digitalcity.projetfinal.mappers.UtilMapper;
import be.digitalcity.projetfinal.models.dto.PaintingPurchaseDTO;
import be.digitalcity.projetfinal.models.entity.PaintingPurchase;
import be.digitalcity.projetfinal.models.entity.abstractClass.BaseEntity;
import be.digitalcity.projetfinal.models.form.PaintingPurchaseForm;
import be.digitalcity.projetfinal.models.form.typeForm.DateForm;
import be.digitalcity.projetfinal.models.form.typeForm.StatusForm;
import be.digitalcity.projetfinal.repository.PaintingPurchaseRepository;
import be.digitalcity.projetfinal.services.PaintingPurchaseService;
import be.digitalcity.projetfinal.services.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PaintingPurchaseServiceImpl implements PaintingPurchaseService {
    private final PaintingPurchaseRepository repository;
    private final PaintingPurchaseMapper mapper;
    private final UtilMapper utilMapper;
    private final UserService userService;

    public PaintingPurchaseServiceImpl(PaintingPurchaseRepository repository, PaintingPurchaseMapper mapper, UtilMapper utilMapper, UserService userService) {
        this.repository = repository;
        this.mapper = mapper;
        this.utilMapper = utilMapper;
        this.userService = userService;
    }

    @Override
    public List<PaintingPurchaseDTO> findAll() {
        return repository.findAll()
                .stream()
                .filter(BaseEntity::isActive)
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public PaintingPurchaseDTO getOne(Long id) {
        return repository.findById(id)
                .filter(BaseEntity::isActive)
                .map(mapper::toDto)
                .orElseThrow(() -> new IllegalArgumentException("The painting purchase doesn't exist"));
    }

    @Override
    public PaintingPurchaseDTO delete(Long id) {
        PaintingPurchase toDelete = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("The painting purchase doesn't exist"));

        toDelete.setActive(false);

        repository.save(toDelete);

        return mapper.toDto(toDelete);
    }

    @Override
    public PaintingPurchaseDTO update(Long id, PaintingPurchaseForm paintingPurchaseForm) {
        PaintingPurchase toUpdate = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("The painting purchase doesn't exist"));

        toUpdate.setAddress(paintingPurchaseForm.getAddress());
        toUpdate.setPaintings(paintingPurchaseForm.getPaintings());
        toUpdate.setStatus(paintingPurchaseForm.getStatus());
        toUpdate.setUser(paintingPurchaseForm.getUser());

        repository.save(toUpdate);

        return mapper.toDto(toUpdate);
    }

    @Override
    public PaintingPurchaseDTO insert(PaintingPurchaseForm paintingPurchaseForm) {
        PaintingPurchase toInsert = mapper.fromFormToEntity(paintingPurchaseForm);

        repository.save(toInsert);

        return mapper.toDto(toInsert);
    }

    @Override
    public List<PaintingPurchaseDTO> findByUser(Long id) {
        this.userService.getOne(id);

        return repository.findPaintingPurchasesByUserId(id)
                .stream()
                .filter(BaseEntity::isActive)
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<PaintingPurchaseDTO> findByStatus(StatusForm status) {
        return repository.findPaintingPurchasesByStatus(utilMapper.fromStatusFormToStatus(status))
                .stream()
                .filter(BaseEntity::isActive)
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<PaintingPurchaseDTO> findByOrderDate(DateForm date) {
        return repository.findPaintingPurchasesByCreatedAt(utilMapper.fromDateFormToDate(date))
                .stream()
                .filter(BaseEntity::isActive)
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }
}
