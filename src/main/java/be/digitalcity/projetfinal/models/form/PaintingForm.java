package be.digitalcity.projetfinal.models.form;

import be.digitalcity.projetfinal.models.entity.PaintingType;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@EqualsAndHashCode
@ToString
@Getter
@Setter
@Validated
public class PaintingForm {
    @NotNull
    @Length(min = 2, max = 100)
    private String name;
    @NotNull
    private String src;
    @NotNull
    private String description;
    @NotNull
    @Min(0)
    private BigDecimal price;
    @NotNull
    private Boolean isAvailable;
    @NotNull
    private Long paintingTypeId;
}
