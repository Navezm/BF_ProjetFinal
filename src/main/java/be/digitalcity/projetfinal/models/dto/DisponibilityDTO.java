package be.digitalcity.projetfinal.models.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@Setter
@EqualsAndHashCode
@ToString
public class DisponibilityDTO {
    private Long id;
    private LocalDate date;
    private boolean status;
}
