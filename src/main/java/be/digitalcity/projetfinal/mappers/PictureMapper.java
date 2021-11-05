package be.digitalcity.projetfinal.mappers;

import be.digitalcity.projetfinal.models.dto.PictureDTO;
import be.digitalcity.projetfinal.models.entity.Picture;
import be.digitalcity.projetfinal.models.form.PictureForm;
import org.springframework.stereotype.Service;

@Service
public class PictureMapper implements BaseMapper<PictureDTO, PictureForm, Picture> {
    private final EventCategoryMapper eventCategoryMapper;

    public PictureMapper(EventCategoryMapper eventCategoryMapper) {
        this.eventCategoryMapper = eventCategoryMapper;
    }

    @Override
    public Picture toEntity(PictureDTO dto) {
        if (dto == null) return null;

        Picture picture = new Picture();

        picture.setAvailable(dto.isAvailable());
        picture.setDescription(dto.getDescription());
        picture.setName(dto.getName());
        picture.setEventCategory(eventCategoryMapper.toEntity(dto.getEventCategory()));
        picture.setPrice(dto.getPrice());
        picture.setSrc(dto.getSrc());
        picture.setId(dto.getId());

        return picture;
    }

    @Override
    public PictureDTO toDto(Picture entity) {
        if (entity == null) return null;

        PictureDTO pictureDTO = new PictureDTO();

        pictureDTO.setDescription(entity.getDescription());
        pictureDTO.setAvailable(entity.isAvailable());
        pictureDTO.setEventCategory(eventCategoryMapper.toDto(entity.getEventCategory()));
        pictureDTO.setPrice(entity.getPrice());
        pictureDTO.setName(entity.getName());
        pictureDTO.setSrc(entity.getSrc());
        pictureDTO.setId(entity.getId());

        return pictureDTO;
    }

    @Override
    public Picture fromFormToEntity(PictureForm form) {
        if (form == null) return null;

        Picture picture = new Picture();

        picture.setSrc(form.getSrc());
        picture.setDescription(form.getDescription());
        picture.setPrice(form.getPrice());
        picture.setName(form.getName());
        picture.setEventCategory(form.getEventCategory());
        picture.setAvailable(form.isAvailable());

        return picture;
    }
}
