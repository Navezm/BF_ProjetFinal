package be.digitalcity.projetfinal.services;

import be.digitalcity.projetfinal.models.dto.DisponibilityDTO;
import be.digitalcity.projetfinal.models.form.DisponibilityForm;

import java.util.List;

public interface DisponibilityService {
    List<DisponibilityDTO> findAll();

    DisponibilityDTO getOne(Long id);

    DisponibilityDTO delete(Long id);

    DisponibilityDTO update(Long id, DisponibilityForm disponibilityForm);

    DisponibilityDTO insert(DisponibilityForm disponibilityForm);
}
