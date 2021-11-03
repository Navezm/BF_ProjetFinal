package be.digitalcity.projetfinal.repository;

import be.digitalcity.projetfinal.models.entity.PaintingPurchase;
import be.digitalcity.projetfinal.models.entity.PicturePurchase;
import be.digitalcity.projetfinal.util.enums.StatusEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface PicturePurchaseRepository extends JpaRepository<PicturePurchase, Long> {

    List<PicturePurchase> findPaintingPurchasesByUserId(Long id);

    List<PicturePurchase> findPaintingPurchasesByStatus(StatusEnum status);

    List<PicturePurchase> findPaintingPurchasesByCreatedAt(LocalDate date);

}
