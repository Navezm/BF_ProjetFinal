package be.digitalcity.projetfinal.services;

import be.digitalcity.projetfinal.models.dto.PaintingQuotationDTO;
import be.digitalcity.projetfinal.models.form.PaintingQuotationForm;

import java.util.List;

public interface PaintingQuotationService {
    List<PaintingQuotationDTO> findAll();

    PaintingQuotationDTO getOne(Long id);

    PaintingQuotationDTO delete(Long id);

    PaintingQuotationDTO update(Long id, PaintingQuotationForm paintingQuotationForm);

    PaintingQuotationDTO insert(PaintingQuotationForm paintingQuotationForm);
}
