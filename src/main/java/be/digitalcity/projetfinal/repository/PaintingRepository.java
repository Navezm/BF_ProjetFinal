package be.digitalcity.projetfinal.repository;

import be.digitalcity.projetfinal.models.entity.Painting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaintingRepository extends JpaRepository<Painting, Long> {

    @Query(value = "SELECT p FROM Painting p WHERE p.paintingType.id = :id")
    List<Painting> findByType(@Param(value = "id") Long id);

    @Query(value = "SELECT p FROM Painting p WHERE p.isAvailable = true")
    List<Painting> findByAvailibility();

}
