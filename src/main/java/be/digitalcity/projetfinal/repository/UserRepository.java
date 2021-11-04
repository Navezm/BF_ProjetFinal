package be.digitalcity.projetfinal.repository;

import be.digitalcity.projetfinal.models.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findUsersByGroupId(Long id);

}
