package be.digitalcity.projetfinal.services;

import be.digitalcity.projetfinal.models.dto.PaintingPurchaseDTO;
import be.digitalcity.projetfinal.models.form.PaintingPurchaseForm;

import java.util.List;

public interface PaintingPurchaseService {
    List<PaintingPurchaseDTO> findAll();

    PaintingPurchaseDTO getOne(Long id);

    PaintingPurchaseDTO delete(Long id);

    PaintingPurchaseDTO update(Long id, PaintingPurchaseForm paintingPurchaseForm);

    PaintingPurchaseDTO insert(PaintingPurchaseForm paintingPurchaseForm);
}
