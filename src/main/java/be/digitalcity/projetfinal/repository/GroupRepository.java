package be.digitalcity.projetfinal.repository;

import be.digitalcity.projetfinal.models.entity.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupRepository extends JpaRepository<Group, Long> {
}
