package be.digitalcity.projetfinal.models.entity;

import be.digitalcity.projetfinal.models.entity.abstractClass.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Table(name = "Security_User")
public class User extends BaseEntity<Long> {
    private String username;
    private String password;
    private String email;

    @OneToMany(targetEntity = Role.class)
    private List<Role> roles;

    @OneToOne(targetEntity = Group.class)
    private Group group;

    @ManyToOne(targetEntity = Address.class)
    private Address address;

    @Override
    public void prePersist() {
        this.createdAt = LocalDate.now();
    }

    @Override
    public void preUpdate() {
        this.updatedAt = LocalDate.now();
    }
}
