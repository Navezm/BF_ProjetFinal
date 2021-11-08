package be.digitalcity.projetfinal.repository;

import be.digitalcity.projetfinal.models.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findUsersByGroupId(Long id);

    @Query("SELECT u FROM User u JOIN u.group g WHERE u.username = :username")
    Optional<User> findUserByUsername(@Param(value = "username") String username);

}
