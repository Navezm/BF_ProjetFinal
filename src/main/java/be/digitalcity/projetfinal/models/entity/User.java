package be.digitalcity.projetfinal.models.entity;

import be.digitalcity.projetfinal.models.entity.abstractClass.BaseEntity;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@EqualsAndHashCode(callSuper = true)
@Entity
@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Security_User")
public class User extends BaseEntity<Long> implements UserDetails {
    @Column(nullable = false, unique = true)
    private String username;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false, unique = true)
    private String email;

    @OneToMany(targetEntity = Role.class, fetch = FetchType.EAGER)

    private List<Role> roles;


    @ManyToOne(targetEntity = Group.class, fetch = FetchType.EAGER)
    private Group group;

    @ManyToOne(targetEntity = Address.class, cascade = {CascadeType.PERSIST}) // Mettre en cascade
    private Address address;

    public void setRoles(List<Role> roles) {
        this.roles.addAll(roles);
    }

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDate.now();
    }

    @PreUpdate
    public void preUpdate() {
        this.updatedAt = LocalDate.now();
    }

    /**
     * Security
     */
    @Column(nullable = false)
    private boolean accountNonExpired;
    @Column(nullable = false)
    private boolean accountNonLocked;
    @Column(nullable = false)
    private boolean creditialsNonExpired;
    @Column(nullable = false)
    private boolean enabled;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
//        List<GrantedAuthority> authorities = Stream.concat(
//                this.getRoles().stream(),
//                this.getGroup()
//                        .getRoleList()
//                        .stream()
//        ).collect(Collectors.toList());
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(this.group);
        authorities.addAll(this.group.getRoleList());
        authorities.addAll(this.roles);

        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return creditialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}
