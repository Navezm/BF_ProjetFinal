package be.digitalcity.projetfinal.models.form;

import be.digitalcity.projetfinal.models.entity.Role;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import java.util.Set;

@EqualsAndHashCode
@ToString
@Getter
@Setter
@Validated
public class GroupForm {
    @NotNull
    private String name;
    @NotNull
    private Set<Role> roleList;
}
