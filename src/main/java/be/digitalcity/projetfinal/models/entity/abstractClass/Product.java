package be.digitalcity.projetfinal.models.entity.abstractClass;

import be.digitalcity.projetfinal.models.entity.abstractClass.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.MappedSuperclass;
import java.math.BigDecimal;

@EqualsAndHashCode(callSuper = true)
@MappedSuperclass
@Data
public abstract class Product extends BaseEntity<Long> {
    private String name;
    private String src;
    private String description;
    private BigDecimal price;
    private boolean isAvailable;
}
