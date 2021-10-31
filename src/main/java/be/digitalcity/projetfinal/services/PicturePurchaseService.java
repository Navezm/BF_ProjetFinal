package be.digitalcity.projetfinal.services;

import be.digitalcity.projetfinal.models.dto.PicturePurchaseDTO;
import be.digitalcity.projetfinal.models.form.PicturePurchaseForm;

import java.util.List;

public interface PicturePurchaseService {
    List<PicturePurchaseDTO> findAll();

    PicturePurchaseDTO getOne(Long id);

    PicturePurchaseDTO delete(Long id);

    PicturePurchaseDTO update(Long id, PicturePurchaseForm picturePurchaseForm);

    PicturePurchaseDTO insert(PicturePurchaseForm picturePurchaseForm);
}
