package be.digitalcity.projetfinal.repository;

import be.digitalcity.projetfinal.models.entity.PaintingPurchase;
import be.digitalcity.projetfinal.models.entity.PaintingQuotation;
import be.digitalcity.projetfinal.util.enums.StatusEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface PaintingQuotationRepository extends JpaRepository<PaintingQuotation, Long> {

    List<PaintingQuotation> findPaintingPurchasesByUserId(Long id);

    List<PaintingQuotation> findPaintingPurchasesByStatus(StatusEnum status);

    List<PaintingQuotation> findPaintingPurchasesByCreatedAt(LocalDate date);

}
