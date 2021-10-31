package be.digitalcity.projetfinal.mappers;

import be.digitalcity.projetfinal.models.dto.ReservationDTO;
import be.digitalcity.projetfinal.models.entity.Reservation;
import be.digitalcity.projetfinal.models.form.ReservationForm;
import org.springframework.stereotype.Service;

@Service
public class ReservationMapper implements BaseMapper<ReservationDTO, ReservationForm, Reservation> {
    private final EventCategoryMapper eventCategoryMapper;
    private final UserMapper userMapper;

    public ReservationMapper(EventCategoryMapper eventCategoryMapper, UserMapper userMapper) {
        this.eventCategoryMapper = eventCategoryMapper;
        this.userMapper = userMapper;
    }

    @Override
    public Reservation toEntity(ReservationDTO dto) {
        if (dto == null) return null;

        Reservation reservation = new Reservation();

        reservation.setStatus(dto.getStatus());
        reservation.setUser(userMapper.toEntity(dto.getUser()));
        reservation.setEndDate(dto.getEndDate());
        reservation.setStartDate(dto.getStartDate());
        reservation.setEventCategory(eventCategoryMapper.toEntity(dto.getEventCategory()));

        return reservation;
    }

    @Override
    public ReservationDTO toDto(Reservation entity) {
        if (entity == null) return null;

        ReservationDTO reservationDTO = new ReservationDTO();

        reservationDTO.setEndDate(entity.getEndDate());
        reservationDTO.setStartDate(entity.getEndDate());
        reservationDTO.setEventCategory(eventCategoryMapper.toDto(entity.getEventCategory()));
        reservationDTO.setUser(userMapper.toDto(entity.getUser()));
        reservationDTO.setStatus(entity.getStatus());

        return reservationDTO;
    }

    @Override
    public Reservation fromFormToEntity(ReservationForm form) {
        if (form == null) return null;

        Reservation reservation = new Reservation();

        reservation.setUser(form.getUser());
        reservation.setStatus(form.getStatus());
        reservation.setEventCategory(form.getEventCategory());
        reservation.setStartDate(form.getStartDate());
        reservation.setEndDate(form.getEndDate());

        return reservation;
    }
}
