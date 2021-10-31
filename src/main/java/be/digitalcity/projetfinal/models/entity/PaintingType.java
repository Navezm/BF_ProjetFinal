package be.digitalcity.projetfinal.models.entity;

import be.digitalcity.projetfinal.models.entity.abstractClass.BaseEntity;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.time.LocalDate;

@EqualsAndHashCode(callSuper = true)
@Entity
@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PaintingType extends BaseEntity<Long> {
    @Column(nullable = false, unique = true)
    private String name;

    @Override
    public void prePersist() {
        this.createdAt= LocalDate.now();
    }

    @Override
    public void preUpdate() {
        this.updatedAt = LocalDate.now();
    }
}
