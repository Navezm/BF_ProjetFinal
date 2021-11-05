package be.digitalcity.projetfinal.services.impl;

import be.digitalcity.projetfinal.mappers.RoleMapper;
import be.digitalcity.projetfinal.models.dto.RoleDTO;
import be.digitalcity.projetfinal.models.entity.Role;
import be.digitalcity.projetfinal.models.entity.abstractClass.BaseEntity;
import be.digitalcity.projetfinal.models.form.RoleForm;
import be.digitalcity.projetfinal.repository.RoleRepository;
import be.digitalcity.projetfinal.services.RoleService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl implements RoleService {
    private final RoleRepository repository;
    private final RoleMapper mapper;

    public RoleServiceImpl(RoleRepository repository, RoleMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public List<RoleDTO> findAll() {
        return repository.findAll()
                .stream()
                .filter(BaseEntity::isActive)
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public RoleDTO getOne(Long id) {
        return repository.findById(id)
                .filter(BaseEntity::isActive)
                .map(mapper::toDto)
                .orElseThrow(() -> new IllegalArgumentException("The role doesn't exist"));
    }

    @Override
    public RoleDTO delete(Long id) {
        Role toDelete = repository.findById(id)
                .filter(BaseEntity::isActive)
                .orElseThrow(() -> new IllegalArgumentException("The role doesn't exist"));

        toDelete.setActive(false);

        repository.save(toDelete);

        return mapper.toDto(toDelete);
    }

    @Override
    public RoleDTO update(Long id, RoleForm roleForm) {
        Role toUpdate = repository.findById(id)
                .filter(BaseEntity::isActive)
                .orElseThrow(() -> new IllegalArgumentException("The role doesn't exist"));

        toUpdate.setName(roleForm.getName());

        repository.save(toUpdate);

        return mapper.toDto(toUpdate);
    }

    @Override
    public RoleDTO insert(RoleForm roleForm) {
        Role toInsert = mapper.fromFormToEntity(roleForm);

        repository.save(toInsert);

        return mapper.toDto(toInsert);
    }
}
