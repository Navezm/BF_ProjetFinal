package be.digitalcity.projetfinal.services;

import be.digitalcity.projetfinal.models.dto.PaintingTypeDTO;

import java.util.List;

public interface ProductService<DTO,FORM> {
    List<DTO> findAll();

    DTO getOne(Long id);

    DTO delete(Long id);

    DTO update(Long id, FORM form);

    DTO insert(FORM form);

    List<DTO> findByType(Long id);

    List<DTO> findByAvailability();
}
