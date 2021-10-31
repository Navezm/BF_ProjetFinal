package be.digitalcity.projetfinal.services;

import be.digitalcity.projetfinal.models.dto.PaintingTypeDTO;
import be.digitalcity.projetfinal.models.form.PaintingTypeForm;

import java.util.List;

public interface PaintingTypeService {
    List<PaintingTypeDTO> findAll();

    PaintingTypeDTO getOne(Long id);

    PaintingTypeDTO delete(Long id);

    PaintingTypeDTO update(Long id, PaintingTypeForm paintingTypeForm);

    PaintingTypeDTO insert(PaintingTypeForm paintingTypeForm);
}
