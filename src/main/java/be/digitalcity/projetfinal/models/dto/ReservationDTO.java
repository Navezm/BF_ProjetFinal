package be.digitalcity.projetfinal.models.dto;

import be.digitalcity.projetfinal.util.enums.StatusEnum;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@Setter
@EqualsAndHashCode
@ToString
public class ReservationDTO {
    private Long id;
    private StatusEnum status;
    private UserDTO user;
    private LocalDate startDate;
    private LocalDate endDate;
    private EventCategoryDTO eventCategory;
}
