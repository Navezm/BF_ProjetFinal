package be.digitalcity.projetfinal.models.entity;

import be.digitalcity.projetfinal.models.entity.abstractClass.Order;
import be.digitalcity.projetfinal.util.enums.ColorEnum;
import be.digitalcity.projetfinal.util.enums.FormatEnum;
import be.digitalcity.projetfinal.util.enums.StatusEnum;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@EqualsAndHashCode(callSuper = true)
@Entity
@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PaintingQuotation extends Order {
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private FormatEnum format;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ColorEnum colorChoice;

    @Column(nullable = false)
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
