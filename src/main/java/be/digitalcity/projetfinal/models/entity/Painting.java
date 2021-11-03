package be.digitalcity.projetfinal.models.entity;

import be.digitalcity.projetfinal.models.entity.abstractClass.Product;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.math.BigDecimal;
import java.time.LocalDate;

@EqualsAndHashCode(callSuper = true)
@Entity
@ToString
@Getter
@Setter
public class Painting extends Product {
    @ManyToOne(targetEntity = PaintingType.class)
    private PaintingType paintingType;

    public Painting(){
        super();
    }

    public Painting(PaintingType paintingType, String name, String src, String description, BigDecimal price, boolean isAvailable) {
        super(name, src, description, price, isAvailable);
        this.paintingType = paintingType;
    }

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDate.now();
    }

    @PreUpdate
    public void preUpdate() {
        this.updatedAt = LocalDate.now();
    }
}
