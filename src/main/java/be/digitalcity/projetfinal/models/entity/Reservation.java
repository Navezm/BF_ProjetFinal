package be.digitalcity.projetfinal.models.entity;

import be.digitalcity.projetfinal.models.entity.abstractClass.Order;
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
public class Reservation extends Order {
    @Column(nullable = false)
    private LocalDate startDate;
    @Column(nullable = false)
    private LocalDate endDate;

    @ManyToOne(targetEntity = EventCategory.class)
    private EventCategory eventCategory;

    public Reservation(StatusEnum status, User user, LocalDate startDate, LocalDate endDate, EventCategory eventCategory) {
        super(status, user);
        this.startDate = startDate;
        this.endDate = endDate;
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

    @Override
    public void changeStatus(StatusEnum status) {
        this.setStatus(status);
    }
}
