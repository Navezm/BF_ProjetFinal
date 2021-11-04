package be.digitalcity.projetfinal.mappers;

import be.digitalcity.projetfinal.models.dto.DisponibilityDTO;
import be.digitalcity.projetfinal.models.entity.Disponibility;
import be.digitalcity.projetfinal.models.form.DisponibilityForm;
import org.springframework.stereotype.Service;

@Service
public class DisponibilityMapper implements BaseMapper<DisponibilityDTO, DisponibilityForm, Disponibility> {
    @Override
    public Disponibility toEntity(DisponibilityDTO dto) {
        if (dto == null) return null;

        return new Disponibility(
                dto.getDate(),
                dto.isStatus()
        );
    }

    @Override
    public DisponibilityDTO toDto(Disponibility entity) {
        if(entity == null) return null;

        DisponibilityDTO disponibilityDTO = new DisponibilityDTO();

        disponibilityDTO.setDate(entity.getDate());
        disponibilityDTO.setStatus(entity.isStatus());

        return disponibilityDTO;
    }

    @Override
    public Disponibility fromFormToEntity(DisponibilityForm form) {
        if (form == null) return null;

        return new Disponibility(
                form.getDate(),
                form.isStatus()
        );
    }
}
