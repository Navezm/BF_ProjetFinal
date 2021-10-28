package be.digitalcity.projetfinal.models.form;

import be.digitalcity.projetfinal.models.entity.EventCategory;
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
public class PictureForm {
    @NotNull
    @Length(min = 2, max = 100)
    private String name;
    @NotNull
    private String src;
    @NotNull
    @Length(min = 0, max = 255)
    private String description;
    @NotNull
    @Min(0)
    private BigDecimal price;
    @NotNull
    private boolean isAvailable;
    @NotNull
    private EventCategory eventCategory;
}
