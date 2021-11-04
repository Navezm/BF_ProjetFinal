package be.digitalcity.projetfinal.repository;

import be.digitalcity.projetfinal.models.entity.PaintingType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaintingTypeRepository extends JpaRepository<PaintingType, Long> {
}
