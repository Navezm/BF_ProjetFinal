package be.digitalcity.projetfinal.mappers;

import be.digitalcity.projetfinal.models.dto.DisponibilityDTO;
import be.digitalcity.projetfinal.models.entity.Disponibility;
import be.digitalcity.projetfinal.models.form.DisponibilityForm;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DisponibilityMapper {
    Disponibility toEntity(DisponibilityDTO disponibilityDTO);

    DisponibilityDTO toDto(Disponibility disponibility);

    Disponibility fromFormToEntity(DisponibilityForm disponibilityForm);
}
