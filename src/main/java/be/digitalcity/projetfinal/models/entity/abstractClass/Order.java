package be.digitalcity.projetfinal.models.entity.abstractClass;

import be.digitalcity.projetfinal.models.entity.User;
import be.digitalcity.projetfinal.models.entity.abstractClass.BaseEntity;
import be.digitalcity.projetfinal.util.enums.StatusEnum;
import lombok.*;

import javax.persistence.MappedSuperclass;
import javax.persistence.OneToOne;
import java.time.LocalDate;

@EqualsAndHashCode(callSuper = true)
@MappedSuperclass
@ToString
@Getter
@Setter
public abstract class Order extends BaseEntity<Long> {
    private StatusEnum status;
    private LocalDate orderDate;

    @OneToOne(targetEntity = User.class)
    private User user;

    Order(){
        this.orderDate = LocalDate.now();
    }

    Order(StatusEnum status, User user){
        this();
        this.status = status;
        this.user = user;
    }

    public abstract void changeStatus(StatusEnum status);
}
