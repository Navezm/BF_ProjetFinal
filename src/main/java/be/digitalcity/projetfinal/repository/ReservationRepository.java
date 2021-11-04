package be.digitalcity.projetfinal.repository;

import be.digitalcity.projetfinal.models.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {

//    @Query(value = "SELECT r FROM Reservation r WHERE r.user.id = :id")
//    List<Reservation> findByUser(@Param(value = "id") Long id);

    List<Reservation> findReservationsByUserId(Long id);

    List<Reservation> findReservationsByStartDate(LocalDate date);

    List<Reservation> findReservationsByEventCategoryId(Long id);

}
