package be.digitalcity.projetfinal.repository;

import be.digitalcity.projetfinal.models.entity.Disponibility;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DisponibilityRepository extends JpaRepository<Disponibility, Long> {
}
