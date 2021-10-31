package be.digitalcity.projetfinal.mappers;

import be.digitalcity.projetfinal.models.dto.PaintingPurchaseDTO;
import be.digitalcity.projetfinal.models.entity.PaintingPurchase;
import be.digitalcity.projetfinal.models.form.PaintingPurchaseForm;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PaintingPurchaseMapper {
    PaintingPurchase toEntity(PaintingPurchaseDTO paintingPurchaseDTO);

    PaintingPurchaseDTO toDto(PaintingPurchase paintingPurchase);

    PaintingPurchase fromFormToEntity(PaintingPurchaseForm paintingPurchaseForm);
}
