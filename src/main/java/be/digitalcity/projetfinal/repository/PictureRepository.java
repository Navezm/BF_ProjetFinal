package be.digitalcity.projetfinal.repository;

import be.digitalcity.projetfinal.models.entity.Picture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PictureRepository extends JpaRepository<Picture, Long> {
}
