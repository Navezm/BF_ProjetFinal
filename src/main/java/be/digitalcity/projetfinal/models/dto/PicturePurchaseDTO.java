package be.digitalcity.projetfinal.models.dto;

import be.digitalcity.projetfinal.util.enums.StatusEnum;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
@ToString
public class PicturePurchaseDTO {
    private Long id;
    private StatusEnum status;
    private UserDTO user;
    private List<PictureDTO> pictures;
}
