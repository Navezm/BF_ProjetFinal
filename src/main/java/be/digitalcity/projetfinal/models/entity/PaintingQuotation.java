package be.digitalcity.projetfinal.models.entity;

import be.digitalcity.projetfinal.models.entity.abstractClass.Order;
import be.digitalcity.projetfinal.util.enums.ColorEnum;
import be.digitalcity.projetfinal.util.enums.FormatEnum;
import be.digitalcity.projetfinal.util.enums.StatusEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.time.LocalDate;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
public class PaintingQuotation extends Order {
    private FormatEnum format;
    private ColorEnum colorChoice;
    private String message;

    @ManyToOne(targetEntity = PaintingType.class)
    private PaintingType paintingType;

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
