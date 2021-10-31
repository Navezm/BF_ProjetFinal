package be.digitalcity.projetfinal.repository;

import be.digitalcity.projetfinal.models.entity.PaintingPurchase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaintingPurchaseRepository extends JpaRepository<PaintingPurchase, Long> {
}
