package be.digitalcity.projetfinal.models.form;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@EqualsAndHashCode
@ToString
public class EmailForm {
    @NotNull
    String name;
    @NotNull
    String email;
    @NotNull
    String message;
}
