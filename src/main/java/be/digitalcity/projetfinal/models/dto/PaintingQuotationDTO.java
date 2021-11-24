package be.digitalcity.projetfinal.models.dto;

import be.digitalcity.projetfinal.util.enums.ColorEnum;
import be.digitalcity.projetfinal.util.enums.FormatEnum;
import be.digitalcity.projetfinal.util.enums.StatusEnum;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode
@ToString
public class PaintingQuotationDTO {
    private Long id;
    private StatusEnum status;
    private UserDTO user;
    private FormatEnum format;
    private ColorEnum colorChoice;
    private String message;
    private PaintingTypeDTO paintingType;
}
