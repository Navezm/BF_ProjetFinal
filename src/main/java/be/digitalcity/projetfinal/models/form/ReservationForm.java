package be.digitalcity.projetfinal.models.form;

import be.digitalcity.projetfinal.models.entity.EventCategory;
import be.digitalcity.projetfinal.models.entity.User;
import be.digitalcity.projetfinal.util.enums.StatusEnum;
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
public class ReservationForm {
    @NotNull
    private StatusEnum status;
    @NotNull
    private User user;
    @NotNull
    private LocalDate startDate;
    @NotNull
    private LocalDate endDate;
    @NotNull
    private EventCategory eventCategory;
}
