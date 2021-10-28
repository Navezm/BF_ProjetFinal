package be.digitalcity.projetfinal.models.entity;

import be.digitalcity.projetfinal.models.entity.abstractClass.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.time.LocalDate;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
public class Address extends BaseEntity<Long> {
    @Column(nullable = false, unique = true)
    private String street;
    @Column(nullable = false, unique = true)
    private String number;
    @Column(nullable = false, unique = true)
    private String postCode;
    @Column(nullable = false, unique = true)
    private String city;

    @Override
    public void prePersist() {
        this.createdAt = LocalDate.now();
    }

    @Override
    public void preUpdate() {
        this.updatedAt = LocalDate.now();
    }
}