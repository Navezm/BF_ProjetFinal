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
@NoArgsConstructor
public class Picture extends Product {
    @ManyToOne(targetEntity = EventCategory.class)
    private EventCategory eventCategory;

    public Picture(String name, String src, String description, BigDecimal price, boolean isAvailable, EventCategory eventCategory) {
        super(name, src, description, price, isAvailable);
        this.eventCategory = eventCategory;
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
