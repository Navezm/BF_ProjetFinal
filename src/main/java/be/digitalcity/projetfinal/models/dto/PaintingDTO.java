package be.digitalcity.projetfinal.models.dto;

import be.digitalcity.projetfinal.models.entity.PaintingType;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

@Getter
@Setter
@EqualsAndHashCode
@ToString
public class PaintingDTO {
    private Long id;
    private String name;
    private String src;
    private String description;
    private BigDecimal price;
    private boolean isAvailable;
    private PaintingTypeDTO paintingType;
}
