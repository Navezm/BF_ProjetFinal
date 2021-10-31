package be.digitalcity.projetfinal.services;

import be.digitalcity.projetfinal.models.dto.GroupDTO;
import be.digitalcity.projetfinal.models.form.GroupForm;

import java.util.List;

public interface GroupService {
    List<GroupDTO> findAll();

    GroupDTO getOne(Long id);

    GroupDTO delete(Long id);

    GroupDTO update(Long id, GroupForm groupForm);

    GroupDTO insert(GroupForm groupForm);
}
