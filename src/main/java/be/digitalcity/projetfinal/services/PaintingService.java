package be.digitalcity.projetfinal.services;

import be.digitalcity.projetfinal.models.dto.PaintingDTO;
import be.digitalcity.projetfinal.models.form.PaintingForm;

import java.util.List;

public interface PaintingService {
    List<PaintingDTO> findAll();

    PaintingDTO getOne(Long id);

    PaintingDTO delete(Long id);

    PaintingDTO update(Long id, PaintingForm paintingForm);

    PaintingDTO insert(PaintingForm paintingForm);
}
