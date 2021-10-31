package be.digitalcity.projetfinal.mappers;

import be.digitalcity.projetfinal.models.dto.PaintingQuotationDTO;
import be.digitalcity.projetfinal.models.entity.PaintingQuotation;
import be.digitalcity.projetfinal.models.form.PaintingQuotationForm;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PaintingQuotationMapper {
    PaintingQuotation toEntity(PaintingQuotationDTO paintingQuotationDTO);

    PaintingQuotationDTO toDto(PaintingQuotation paintingQuotation);

    PaintingQuotation fromFormToEntity(PaintingQuotationForm paintingQuotationForm);
}
