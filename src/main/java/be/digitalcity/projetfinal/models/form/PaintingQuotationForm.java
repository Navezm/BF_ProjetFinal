package be.digitalcity.projetfinal.models.form;

import be.digitalcity.projetfinal.models.entity.PaintingType;
import be.digitalcity.projetfinal.models.entity.User;
import be.digitalcity.projetfinal.util.enums.ColorEnum;
import be.digitalcity.projetfinal.util.enums.FormatEnum;
import be.digitalcity.projetfinal.util.enums.StatusEnum;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;

@EqualsAndHashCode
@ToString
@Getter
@Setter
@Validated
public class PaintingQuotationForm {
    @NotNull
    private StatusEnum status;
    @NotNull
    private User user;
    @NotNull
    private FormatEnum format;
    @NotNull
    private ColorEnum colorChoice;
    @NotNull
    private String message;
    @NotNull
    private PaintingType paintingType;
}
