package be.digitalcity.projetfinal.mappers;

import be.digitalcity.projetfinal.models.dto.PaintingDTO;
import be.digitalcity.projetfinal.models.entity.Painting;
import be.digitalcity.projetfinal.models.form.PaintingForm;
import org.springframework.stereotype.Service;

@Service
public class PaintingMapper implements BaseMapper<PaintingDTO, PaintingForm, Painting> {
    private final PaintingTypeMapper paintingTypeMapper;

    public PaintingMapper(PaintingTypeMapper paintingTypeMapper) {
        this.paintingTypeMapper = paintingTypeMapper;
    }

    @Override
    public Painting toEntity(PaintingDTO dto) {
        if (dto == null) return null;

        Painting painting = new Painting();

        painting.setAvailable(dto.isAvailable());
        painting.setDescription(dto.getDescription());
        painting.setName(dto.getName());
        painting.setPrice(dto.getPrice());
        painting.setSrc(dto.getSrc());
        painting.setPaintingType(paintingTypeMapper.toEntity(dto.getPaintingType()));

        return painting;
    }

    @Override
    public PaintingDTO toDto(Painting entity) {
        if (entity == null) return null;

        PaintingDTO paintingDTO = new PaintingDTO();

        paintingDTO.setPaintingType(paintingTypeMapper.toDto(entity.getPaintingType()));
        paintingDTO.setAvailable(entity.isAvailable());
        paintingDTO.setDescription(entity.getDescription());
        paintingDTO.setPrice(entity.getPrice());
        paintingDTO.setName(entity.getName());
        paintingDTO.setSrc(entity.getSrc());
        paintingDTO.setId(entity.getId());

        return paintingDTO;
    }

    @Override
    public Painting fromFormToEntity(PaintingForm form) {
        if (form == null) return null;

        Painting painting = new Painting();

        painting.setPrice(form.getPrice());
        painting.setPaintingType(form.getPaintingType());
        painting.setSrc(form.getSrc());
        painting.setName(form.getName());
        painting.setDescription(form.getDescription());
        painting.setPrice(form.getPrice());

        return painting;
    }
}
