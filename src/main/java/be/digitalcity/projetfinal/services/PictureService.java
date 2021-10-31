package be.digitalcity.projetfinal.services;

import be.digitalcity.projetfinal.models.dto.PaintingDTO;
import be.digitalcity.projetfinal.models.form.PictureForm;

import java.util.List;

public interface PictureService {
    List<PaintingDTO> findAll();

    PaintingDTO getOne(Long id);

    PaintingDTO delete(Long id);

    PaintingDTO update(Long id, PictureForm pictureForm);

    PaintingDTO insert(PictureForm pictureForm);
}
