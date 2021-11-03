package be.digitalcity.projetfinal.repository;

import be.digitalcity.projetfinal.models.entity.Picture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PictureRepository extends JpaRepository<Picture, Long> {

    @Query(value = "SELECT p FROM Picture p WHERE p.eventCategory.id = :id")
    List<Picture> findByType(@Param(value = "id") Long id);

    @Query(value = "SELECT p FROM Picture p WHERE p.isAvailable = true")
    List<Picture> findByAvailibility();

}
