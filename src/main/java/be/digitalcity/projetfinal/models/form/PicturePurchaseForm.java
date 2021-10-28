package be.digitalcity.projetfinal.models.form;

import be.digitalcity.projetfinal.models.entity.Picture;
import be.digitalcity.projetfinal.models.entity.User;
import be.digitalcity.projetfinal.util.enums.StatusEnum;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import java.util.List;

@EqualsAndHashCode
@ToString
@Getter
@Setter
@Validated
public class PicturePurchaseForm {
    @NotNull
    private StatusEnum status;
    @NotNull
    private User user;
    @NotNull
    private List<Picture> pictures;
}
