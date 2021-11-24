package be.digitalcity.projetfinal.models.form;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@EqualsAndHashCode
@ToString
@Getter
@Setter
@Validated
public class DisponibilityForm {
    @NotNull
    private LocalDate date;
    @NotNull
    private boolean status;
}
