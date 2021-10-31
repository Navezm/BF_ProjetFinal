package be.digitalcity.projetfinal.repository;

import be.digitalcity.projetfinal.models.entity.PicturePurchase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PicturePurchaseRepository extends JpaRepository<PicturePurchase, Long> {
}
