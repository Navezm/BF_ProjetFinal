package be.digitalcity.projetfinal.services;

import be.digitalcity.projetfinal.models.form.typeForm.DateForm;
import be.digitalcity.projetfinal.models.form.typeForm.StatusForm;
import be.digitalcity.projetfinal.util.enums.StatusEnum;

import java.time.LocalDate;
import java.util.List;

public interface OrderService<DTO, FORM> {
    List<DTO> findAll();

    DTO getOne(Long id);

    DTO delete(Long id);

    DTO update(Long id, FORM paintingPurchaseForm);

    DTO insert(FORM paintingPurchaseForm);

    List<DTO> findByUser(Long id);

    List<DTO> findByStatus(StatusForm status);

    List<DTO> findByOrderDate(DateForm date);
}
