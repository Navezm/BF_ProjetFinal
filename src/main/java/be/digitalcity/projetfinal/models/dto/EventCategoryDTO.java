package be.digitalcity.projetfinal.models.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode
@ToString
public class EventCategoryDTO {
    private Long id;
    private String name;
}
