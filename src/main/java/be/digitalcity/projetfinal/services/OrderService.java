package be.digitalcity.projetfinal.services;

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

    List<DTO> findByStatus(StatusEnum status);

    List<DTO> findByOrderDate(LocalDate date);
}
