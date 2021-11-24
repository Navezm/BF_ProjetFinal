package be.digitalcity.projetfinal.repository;

import be.digitalcity.projetfinal.models.entity.PaintingPurchase;
import be.digitalcity.projetfinal.util.enums.StatusEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface PaintingPurchaseRepository extends JpaRepository<PaintingPurchase, Long> {

    List<PaintingPurchase> findPaintingPurchasesByUserId(Long id);

    List<PaintingPurchase> findPaintingPurchasesByStatus(StatusEnum status);

    List<PaintingPurchase> findPaintingPurchasesByCreatedAt(LocalDate date);

}
