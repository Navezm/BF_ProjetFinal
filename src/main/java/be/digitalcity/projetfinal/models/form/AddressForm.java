package be.digitalcity.projetfinal.models.form;

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
public class AddressForm {
    @NotNull
    private String street;
    @NotNull
    private String number;
    @NotNull
    private String postCode;
    @NotNull
    private String city;
}
