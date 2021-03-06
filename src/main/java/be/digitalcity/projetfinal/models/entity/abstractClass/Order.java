package be.digitalcity.projetfinal.models.entity.abstractClass;

import be.digitalcity.projetfinal.models.entity.User;
import be.digitalcity.projetfinal.models.entity.abstractClass.BaseEntity;
import be.digitalcity.projetfinal.util.enums.StatusEnum;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@EqualsAndHashCode(callSuper = true)
@MappedSuperclass
@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class Order extends BaseEntity<Long> {
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusEnum status;

    public void setStatus(StatusEnum status) {
        this.status = status;
    }

    @OneToOne(targetEntity = User.class)
    private User user;

    public abstract void changeStatus(StatusEnum status);
}
