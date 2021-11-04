package be.digitalcity.projetfinal.mappers;

import be.digitalcity.projetfinal.models.dto.PaintingPurchaseDTO;
import be.digitalcity.projetfinal.models.entity.Painting;
import be.digitalcity.projetfinal.models.entity.PaintingPurchase;
import be.digitalcity.projetfinal.models.form.PaintingPurchaseForm;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class PaintingPurchaseMapper implements BaseMapper<PaintingPurchaseDTO, PaintingPurchaseForm, PaintingPurchase> {
    private final AddressMapper addressMapper;
    private final PaintingMapper paintingMapper;
    private final UserMapper userMapper;

    public PaintingPurchaseMapper(AddressMapper addressMapper, PaintingMapper paintingMapper, UserMapper userMapper) {
        this.addressMapper = addressMapper;
        this.paintingMapper = paintingMapper;
        this.userMapper = userMapper;
    }

    @Override
    public PaintingPurchase toEntity(PaintingPurchaseDTO dto) {
        if (dto == null) return null;

        PaintingPurchase paintingPurchase = new PaintingPurchase();

        paintingPurchase.setStatus(dto.getStatus());
        paintingPurchase.setPaintings(dto.getPaintings().stream()
                .map(paintingMapper::toEntity)
                .collect(Collectors.toList()));
        paintingPurchase.setAddress(addressMapper.toEntity(dto.getAddress()));
        paintingPurchase.setUser(userMapper.toEntity(dto.getUser()));

        return paintingPurchase;
    }

    @Override
    public PaintingPurchaseDTO toDto(PaintingPurchase entity) {
        if (entity == null) return null;

        PaintingPurchaseDTO paintingPurchaseDTO = new PaintingPurchaseDTO();

        paintingPurchaseDTO.setStatus(entity.getStatus());
        paintingPurchaseDTO.setPaintings(entity.getPaintings().stream()
                .map(paintingMapper::toDto)
                .collect(Collectors.toList()));
        paintingPurchaseDTO.setAddress(addressMapper.toDto(entity.getAddress()));
        paintingPurchaseDTO.setUser(userMapper.toDto(entity.getUser()));
        paintingPurchaseDTO.setId(entity.getId());

        return paintingPurchaseDTO;
    }

    @Override
    public PaintingPurchase fromFormToEntity(PaintingPurchaseForm form) {
        if (form == null) return null;

        PaintingPurchase paintingPurchase = new PaintingPurchase();

        paintingPurchase.setUser(form.getUser());
        paintingPurchase.setStatus(form.getStatus());
        paintingPurchase.setPaintings(form.getPaintings());
        paintingPurchase.setAddress(form.getAddress());

        return paintingPurchase;
    }
}
