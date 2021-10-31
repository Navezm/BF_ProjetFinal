package be.digitalcity.projetfinal.mappers;

import be.digitalcity.projetfinal.models.dto.ReservationDTO;
import be.digitalcity.projetfinal.models.entity.Reservation;
import be.digitalcity.projetfinal.models.form.ReservationForm;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ReservationMapper {
    Reservation toEntity(ReservationDTO reservationDTO);

    ReservationDTO toDto(Reservation reservation);

    Reservation fromFormToEntity(ReservationForm reservationForm);
}
