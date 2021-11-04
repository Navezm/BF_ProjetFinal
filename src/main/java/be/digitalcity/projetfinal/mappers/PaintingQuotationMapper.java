package be.digitalcity.projetfinal.mappers;

import be.digitalcity.projetfinal.models.dto.PaintingQuotationDTO;
import be.digitalcity.projetfinal.models.entity.Painting;
import be.digitalcity.projetfinal.models.entity.PaintingQuotation;
import be.digitalcity.projetfinal.models.form.PaintingQuotationForm;
import org.springframework.stereotype.Service;

@Service
public class PaintingQuotationMapper implements BaseMapper<PaintingQuotationDTO, PaintingQuotationForm, PaintingQuotation> {
    private final PaintingTypeMapper paintingTypeMapper;
    private final UserMapper userMapper;

    public PaintingQuotationMapper(PaintingTypeMapper paintingTypeMapper, UserMapper userMapper) {
        this.paintingTypeMapper = paintingTypeMapper;
        this.userMapper = userMapper;
    }

    @Override
    public PaintingQuotation toEntity(PaintingQuotationDTO dto) {
        if (dto == null) return null;

        PaintingQuotation paintingQuotation = new PaintingQuotation();

        paintingQuotation.setStatus(dto.getStatus());
        paintingQuotation.setUser(userMapper.toEntity(dto.getUser()));
        paintingQuotation.setColorChoice(dto.getColorChoice());
        paintingQuotation.setPaintingType(paintingTypeMapper.toEntity(dto.getPaintingType()));
        paintingQuotation.setFormat(dto.getFormat());
        paintingQuotation.setMessage(dto.getMessage());

        return paintingQuotation;
    }

    @Override
    public PaintingQuotationDTO toDto(PaintingQuotation entity) {
        if (entity == null) return null;

        PaintingQuotationDTO paintingQuotationDTO = new PaintingQuotationDTO();

        paintingQuotationDTO.setMessage(entity.getMessage());
        paintingQuotationDTO.setFormat(entity.getFormat());
        paintingQuotationDTO.setColorChoice(entity.getColorChoice());
        paintingQuotationDTO.setPaintingType(paintingTypeMapper.toDto(entity.getPaintingType()));
        paintingQuotationDTO.setUser(userMapper.toDto(entity.getUser()));
        paintingQuotationDTO.setStatus(entity.getStatus());
        paintingQuotationDTO.setId(entity.getId());

        return paintingQuotationDTO;
    }

    @Override
    public PaintingQuotation fromFormToEntity(PaintingQuotationForm form) {
        if (form == null) return null;

        PaintingQuotation paintingQuotation = new PaintingQuotation();

        paintingQuotation.setPaintingType(form.getPaintingType());
        paintingQuotation.setUser(form.getUser());
        paintingQuotation.setStatus(form.getStatus());
        paintingQuotation.setFormat(form.getFormat());
        paintingQuotation.setMessage(form.getMessage());
        paintingQuotation.setColorChoice(form.getColorChoice());

        return paintingQuotation;
    }
}
