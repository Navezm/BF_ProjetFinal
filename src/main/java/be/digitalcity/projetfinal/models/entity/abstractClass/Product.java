package be.digitalcity.projetfinal.models.entity.abstractClass;

import be.digitalcity.projetfinal.models.entity.abstractClass.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.math.BigDecimal;

@EqualsAndHashCode(callSuper = true)
@MappedSuperclass
@Data
public abstract class Product extends BaseEntity<Long> {
    @Column(unique = true ,nullable = false)
    private String name;

    @Column(unique = true ,nullable = false)
    private String src;

    @Column(unique = true ,nullable = false)
    private String description;

    @Column(nullable = false)
    private BigDecimal price;

    @Column(nullable = false)
    private boolean isAvailable;
}
