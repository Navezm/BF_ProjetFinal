package be.digitalcity.projetfinal.models.entity.abstractClass;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

@MappedSuperclass
public abstract class BaseEntity<TKey extends Serializable> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private TKey id;

    @Column(name = "isActive", nullable = false)
    protected boolean isActive = true;

    @Column(name = "createdAt", nullable = false)
    protected LocalDate createdAt;

    @Column(name = "updatedAt")
    protected LocalDate updatedAt;

    public TKey getId() {
        return id;
    }

    public void setId(TKey id) {
        this.id = id;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDate getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDate updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BaseEntity)) return false;
        BaseEntity<?> that = (BaseEntity<?>) o;
        return isActive == that.isActive && Objects.equals(id, that.id) && Objects.equals(createdAt, that.createdAt) && Objects.equals(updatedAt, that.updatedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, isActive, createdAt, updatedAt);
    }

    public abstract void prePersist();
    public abstract void preUpdate();
}
