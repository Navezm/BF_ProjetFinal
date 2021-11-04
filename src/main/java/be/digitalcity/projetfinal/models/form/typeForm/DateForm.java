package be.digitalcity.projetfinal.models.form.typeForm;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@Validated
public class DateForm {
    @NotNull
    private LocalDate date;
}
