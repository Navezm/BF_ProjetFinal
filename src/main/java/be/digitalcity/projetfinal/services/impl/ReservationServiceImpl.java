package be.digitalcity.projetfinal.services.impl;

import be.digitalcity.projetfinal.mappers.ReservationMapper;
import be.digitalcity.projetfinal.mappers.UtilMapper;
import be.digitalcity.projetfinal.models.dto.ReservationDTO;
import be.digitalcity.projetfinal.models.entity.Reservation;
import be.digitalcity.projetfinal.models.form.ReservationForm;
import be.digitalcity.projetfinal.models.form.typeForm.DateForm;
import be.digitalcity.projetfinal.repository.ReservationRepository;
import be.digitalcity.projetfinal.services.ReservationService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReservationServiceImpl implements ReservationService {
    private final ReservationRepository repository;
    private final ReservationMapper mapper;
    private final UtilMapper utilMapper;

    public ReservationServiceImpl(ReservationRepository repository, ReservationMapper mapper, UtilMapper utilMapper) {
        this.repository = repository;
        this.mapper = mapper;
        this.utilMapper = utilMapper;
    }

    @Override
    public List<ReservationDTO> findAll() {
        return repository.findAll()
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public ReservationDTO getOne(Long id) {
        return repository.findById(id)
                .map(mapper::toDto)
                .orElseThrow(() -> new IllegalArgumentException("The reservation doesn't exist"));
    }

    @Override
    public ReservationDTO delete(Long id) {
        Reservation toDelete = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("The reservation doesn't exist"));

        toDelete.setActive(false);

        repository.save(toDelete);

        return mapper.toDto(toDelete);
    }

    @Override
    public ReservationDTO update(Long id, ReservationForm reservationForm) {
        Reservation toUpdate = repository.findById(id)
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
        return repository.findReservationsByUserId(id)
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<ReservationDTO> findByDate(DateForm date) {
        return repository.findReservationsByStartDate(utilMapper.fromDateFormToDate(date))
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<ReservationDTO> findByEventType(Long id) {
        return repository.findReservationsByEventCategoryId(id)
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }
}
