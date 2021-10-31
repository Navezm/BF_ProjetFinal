package be.digitalcity.projetfinal.models.entity;

import be.digitalcity.projetfinal.models.entity.abstractClass.Order;
import be.digitalcity.projetfinal.util.enums.StatusEnum;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.time.LocalDate;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PicturePurchase extends Order {
    @ManyToMany(targetEntity = Picture.class)
    private List<Picture> pictures;

    @Override
    public void prePersist() {
        this.createdAt = LocalDate.now();
    }

    @Override
    public void preUpdate() {
        this.updatedAt = LocalDate.now();
    }

    @Override
    public void changeStatus(StatusEnum status) {
        this.setStatus(status);
    }
}
