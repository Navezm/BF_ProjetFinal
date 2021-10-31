package be.digitalcity.projetfinal.services;

import be.digitalcity.projetfinal.models.dto.ReservationDTO;
import be.digitalcity.projetfinal.models.form.ReservationForm;

import java.util.List;

public interface ReservationService {
    List<ReservationDTO> findAll();

    ReservationDTO getOne(Long id);

    ReservationDTO delete(Long id);

    ReservationDTO update(Long id, ReservationForm reservationForm);

    ReservationDTO insert(ReservationForm reservationForm);
}
