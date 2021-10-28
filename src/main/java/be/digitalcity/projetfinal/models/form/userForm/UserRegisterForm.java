package be.digitalcity.projetfinal.models.form.userForm;

import be.digitalcity.projetfinal.models.entity.Address;
import be.digitalcity.projetfinal.models.entity.Role;
import be.digitalcity.projetfinal.models.form.AddressForm;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import java.util.List;

@EqualsAndHashCode
@ToString
@Getter
@Setter
@Validated
public class UserRegisterForm {
    @Length(min = 5, max = 60)
    @NotNull
    private String username;
    @NotNull
    private String email;
    @NotNull
    private AddressForm address;
    @Length(min = 6)
    @NotNull
    private String password;
}
