package be.digitalcity.projetfinal.repository;

import be.digitalcity.projetfinal.models.entity.PaintingQuotation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaintingQuotationRepository extends JpaRepository<PaintingQuotation, Long> {
}
