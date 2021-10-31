package be.digitalcity.projetfinal.mappers;

import be.digitalcity.projetfinal.models.dto.PaintingTypeDTO;
import be.digitalcity.projetfinal.models.entity.PaintingType;
import be.digitalcity.projetfinal.models.form.PaintingTypeForm;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PaintingTypeMapper {
    PaintingType toEntity(PaintingTypeDTO paintingTypeDTO);

    PaintingTypeDTO toDto(PaintingType paintingType);

    PaintingType fromFormToEntity(PaintingTypeForm paintingTypeForm);
}
