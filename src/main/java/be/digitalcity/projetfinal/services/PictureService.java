package be.digitalcity.projetfinal.services;

import be.digitalcity.projetfinal.models.dto.PaintingDTO;
import be.digitalcity.projetfinal.models.dto.PictureDTO;
import be.digitalcity.projetfinal.models.form.PictureForm;

import java.util.List;

public interface PictureService {
    List<PictureDTO> findAll();

    PictureDTO getOne(Long id);

    PictureDTO delete(Long id);

    PictureDTO update(Long id, PictureForm pictureForm);

    PictureDTO insert(PictureForm pictureForm);
}
