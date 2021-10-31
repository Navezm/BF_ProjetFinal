package be.digitalcity.projetfinal.models.entity;

import be.digitalcity.projetfinal.models.entity.abstractClass.BaseEntity;
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
@Table(name = "Security_Group")
public class Role extends BaseEntity<Long> {
    @Column(nullable = false, unique = true)
    private String name;

    @Override
    public void prePersist() {
        this.createdAt = LocalDate.now();
    }

    @Override
    public void preUpdate() {
        this.updatedAt = LocalDate.now();
    }
}
