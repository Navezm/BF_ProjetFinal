package be.digitalcity.projetfinal.mappers;

import be.digitalcity.projetfinal.models.dto.PaintingDTO;
import be.digitalcity.projetfinal.models.entity.Painting;
import be.digitalcity.projetfinal.models.form.PaintingForm;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PaintingMapper {
    Painting toEntity(PaintingDTO paintingDTO);

    PaintingDTO toDto(Painting painting);

    Painting fromFormToEntity(PaintingForm paintingForm);
}
