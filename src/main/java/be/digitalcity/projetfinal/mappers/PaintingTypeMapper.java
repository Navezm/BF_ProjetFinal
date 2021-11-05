package be.digitalcity.projetfinal.mappers;

import be.digitalcity.projetfinal.models.dto.PaintingTypeDTO;
import be.digitalcity.projetfinal.models.entity.PaintingType;
import be.digitalcity.projetfinal.models.form.PaintingTypeForm;
import org.springframework.stereotype.Service;

@Service
public class PaintingTypeMapper implements BaseMapper<PaintingTypeDTO, PaintingTypeForm, PaintingType> {
    @Override
    public PaintingType toEntity(PaintingTypeDTO dto) {
        if (dto == null) return null;

        PaintingType paintingType = new PaintingType(
                dto.getName()
        );
        paintingType.setId(dto.getId());

        return paintingType;
    }

    @Override
    public PaintingTypeDTO toDto(PaintingType entity) {
        if (entity == null) return null;

        PaintingTypeDTO paintingTypeDTO = new PaintingTypeDTO();

        paintingTypeDTO.setName(entity.getName());
        paintingTypeDTO.setId(entity.getId());

        return paintingTypeDTO;
    }

    @Override
    public PaintingType fromFormToEntity(PaintingTypeForm form) {
        if (form == null) return null;

        return new PaintingType(
                form.getName()
        );
    }
}
