package be.digitalcity.projetfinal.mappers;

import be.digitalcity.projetfinal.models.dto.PictureDTO;
import be.digitalcity.projetfinal.models.entity.Picture;
import be.digitalcity.projetfinal.models.form.PictureForm;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PictureMapper {
    Picture toEntity(PictureDTO pictureDTO);

    PictureDTO toDto(Picture picture);

    Picture fromFormToEntity(PictureForm pictureForm);
}
