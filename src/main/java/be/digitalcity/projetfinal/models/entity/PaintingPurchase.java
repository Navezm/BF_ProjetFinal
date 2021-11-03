package be.digitalcity.projetfinal.models.entity;

import be.digitalcity.projetfinal.models.entity.abstractClass.Order;
import be.digitalcity.projetfinal.util.enums.StatusEnum;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@ToString
@Getter
@Setter
@NoArgsConstructor
public class PaintingPurchase extends Order {
    @ManyToMany(targetEntity = Painting.class)
    private List<Painting> paintings;

    @ManyToOne(targetEntity = Address.class)
    private Address address;

    public PaintingPurchase(StatusEnum status, User user, List<Painting> paintings, Address address) {
        super(status, user);
        this.paintings = paintings;
        this.address = address;
    }

    @Override
    public void changeStatus(StatusEnum status) {
        this.setStatus(status);
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
