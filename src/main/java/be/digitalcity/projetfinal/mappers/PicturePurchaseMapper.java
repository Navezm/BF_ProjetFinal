package be.digitalcity.projetfinal.mappers;

import be.digitalcity.projetfinal.models.dto.PicturePurchaseDTO;
import be.digitalcity.projetfinal.models.entity.PicturePurchase;
import be.digitalcity.projetfinal.models.form.PicturePurchaseForm;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class PicturePurchaseMapper implements BaseMapper<PicturePurchaseDTO, PicturePurchaseForm, PicturePurchase> {
    private final PictureMapper pictureMapper;
    private final UserMapper userMapper;

    public PicturePurchaseMapper(PictureMapper pictureMapper, UserMapper userMapper) {
        this.pictureMapper = pictureMapper;
        this.userMapper = userMapper;
    }

    @Override
    public PicturePurchase toEntity(PicturePurchaseDTO dto) {
        if (dto == null) return null;

        PicturePurchase picturePurchase = new PicturePurchase();

        picturePurchase.setPictures(dto.getPictures().stream()
                .map(pictureMapper::toEntity)
                .collect(Collectors.toList()));
        picturePurchase.setStatus(dto.getStatus());
        picturePurchase.setUser(userMapper.toEntity(dto.getUser()));

        return picturePurchase;
    }

    @Override
    public PicturePurchaseDTO toDto(PicturePurchase entity) {
        if (entity == null) return null;

        PicturePurchaseDTO picturePurchaseDTO = new PicturePurchaseDTO();

        picturePurchaseDTO.setPictures(entity.getPictures().stream()
                .map(pictureMapper::toDto)
                .collect(Collectors.toList()));
        picturePurchaseDTO.setUser(userMapper.toDto(entity.getUser()));
        picturePurchaseDTO.setStatus(entity.getStatus());
        picturePurchaseDTO.setId(entity.getId());

        return picturePurchaseDTO;
    }

    @Override
    public PicturePurchase fromFormToEntity(PicturePurchaseForm form) {
        if (form == null) return null;

        PicturePurchase picturePurchase = new PicturePurchase();

        picturePurchase.setUser(form.getUser());
        picturePurchase.setStatus(form.getStatus());
        picturePurchase.setPictures(form.getPictures());

        return picturePurchase;
    }
}
