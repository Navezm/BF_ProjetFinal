package be.digitalcity.projetfinal.mappers;

import be.digitalcity.projetfinal.models.dto.PicturePurchaseDTO;
import be.digitalcity.projetfinal.models.entity.PicturePurchase;
import be.digitalcity.projetfinal.models.form.PicturePurchaseForm;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PicturePurchaseMapper {
    PicturePurchase toEntity(PicturePurchaseDTO picturePurchaseDTO);

    PicturePurchaseDTO toDto(PicturePurchase picturePurchase);

    PicturePurchase fromFormToEntity(PicturePurchaseForm picturePurchaseForm);
}
