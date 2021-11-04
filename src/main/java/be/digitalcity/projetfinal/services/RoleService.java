package be.digitalcity.projetfinal.services;

import be.digitalcity.projetfinal.models.dto.RoleDTO;
import be.digitalcity.projetfinal.models.form.RoleForm;

import java.util.List;

public interface RoleService {
    List<RoleDTO> findAll();

    RoleDTO getOne(Long id);

    RoleDTO delete(Long id);

    RoleDTO update(Long id, RoleForm roleForm);

    RoleDTO insert(RoleForm roleForm);
}
