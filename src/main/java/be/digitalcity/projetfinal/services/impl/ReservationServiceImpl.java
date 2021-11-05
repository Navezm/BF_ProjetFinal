package be.digitalcity.projetfinal.services.impl;

import be.digitalcity.projetfinal.mappers.ReservationMapper;
import be.digitalcity.projetfinal.mappers.UtilMapper;
import be.digitalcity.projetfinal.models.dto.ReservationDTO;
import be.digitalcity.projetfinal.models.entity.Reservation;
import be.digitalcity.projetfinal.models.entity.abstractClass.BaseEntity;
import be.digitalcity.projetfinal.models.form.ReservationForm;
import be.digitalcity.projetfinal.models.form.typeForm.DateForm;
import be.digitalcity.projetfinal.repository.ReservationRepository;
import be.digitalcity.projetfinal.services.EventCategoryService;
import be.digitalcity.projetfinal.services.ReservationService;
import be.digitalcity.projetfinal.services.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReservationServiceImpl implements ReservationService {
    private final ReservationRepository repository;
    private final ReservationMapper mapper;
    private final UtilMapper utilMapper;
    private final UserService userService;
    private final EventCategoryService eventCategoryService;

    public ReservationServiceImpl(ReservationRepository repository, ReservationMapper mapper, UtilMapper utilMapper, UserService userService, EventCategoryService eventCategoryService) {
        this.repository = repository;
        this.mapper = mapper;
        this.utilMapper = utilMapper;
        this.userService = userService;
        this.eventCategoryService = eventCategoryService;
    }

    @Override
    public List<ReservationDTO> findAll() {
        return repository.findAll()
                .stream()
                .filter(BaseEntity::isActive)
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public ReservationDTO getOne(Long id) {
        return repository.findById(id)
                .filter(BaseEntity::isActive)
                .map(mapper::toDto)
                .orElseThrow(() -> new IllegalArgumentException("The reservation doesn't exist"));
    }

    @Override
    public ReservationDTO delete(Long id) {
        Reservation toDelete = repository.findById(id)
                .filter(BaseEntity::isActive)
                .orElseThrow(() -> new IllegalArgumentException("The reservation doesn't exist"));

        toDelete.setActive(false);

        repository.save(toDelete);

        return mapper.toDto(toDelete);
    }

    @Override
    public ReservationDTO update(Long id, ReservationForm reservationForm) {
        Reservation toUpdate = repository.findById(id)
                .filter(BaseEntity::isActive)
                .orElseThrow(() -> new IllegalArgumentException("The reservation doesn't exist"));

        toUpdate.setEndDate(reservationForm.getEndDate());
        toUpdate.setStartDate(reservationForm.getStartDate());
        toUpdate.setEventCategory(reservationForm.getEventCategory());
        toUpdate.setUser(reservationForm.getUser());
        toUpdate.setStatus(reservationForm.getStatus());

        repository.save(toUpdate);

        return mapper.toDto(toUpdate);
    }

    @Override
    public ReservationDTO insert(ReservationForm reservationForm) {
        Reservation toInsert = mapper.fromFormToEntity(reservationForm);

        repository.save(toInsert);

        return mapper.toDto(toInsert);
    }

    @Override
    public List<ReservationDTO> findByUser(Long id) {
        this.userService.getOne(id);

        return repository.findReservationsByUserId(id)
                .stream()
                .filter(BaseEntity::isActive)
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<ReservationDTO> findByDate(DateForm date) {
        return repository.findReservationsByStartDate(utilMapper.fromDateFormToDate(date))
                .stream()
                .filter(BaseEntity::isActive)
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<ReservationDTO> findByEventType(Long id) {
        this.eventCategoryService.getOne(id);

        return repository.findReservationsByEventCategoryId(id)
                .stream()
                .filter(BaseEntity::isActive)
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }
}
