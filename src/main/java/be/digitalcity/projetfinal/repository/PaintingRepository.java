package be.digitalcity.projetfinal.repository;

import be.digitalcity.projetfinal.models.entity.Painting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaintingRepository extends JpaRepository<Painting, Long> {
}
