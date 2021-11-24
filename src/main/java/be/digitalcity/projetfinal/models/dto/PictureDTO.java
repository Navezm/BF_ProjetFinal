package be.digitalcity.projetfinal.models.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

@Getter
@Setter
@EqualsAndHashCode
@ToString
public class PictureDTO {
    private Long id;
    private String name;
    private String src;
    private String description;
    private BigDecimal price;
    private boolean isAvailable;
    private EventCategoryDTO eventCategory;
}
