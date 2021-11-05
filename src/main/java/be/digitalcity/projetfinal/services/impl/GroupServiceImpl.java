package be.digitalcity.projetfinal.services.impl;

import be.digitalcity.projetfinal.mappers.GroupMapper;
import be.digitalcity.projetfinal.models.dto.GroupDTO;
import be.digitalcity.projetfinal.models.entity.Group;
import be.digitalcity.projetfinal.models.entity.abstractClass.BaseEntity;
import be.digitalcity.projetfinal.models.form.GroupForm;
import be.digitalcity.projetfinal.repository.GroupRepository;
import be.digitalcity.projetfinal.services.GroupService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GroupServiceImpl implements GroupService {
    private final GroupRepository repository;
    private final GroupMapper mapper;

    public GroupServiceImpl(GroupRepository repository, GroupMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public List<GroupDTO> findAll() {
        return repository.findAll()
                .stream()
                .filter(BaseEntity::isActive)
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public GroupDTO getOne(Long id) {
        return repository.findById(id)
                .filter(BaseEntity::isActive)
                .map(mapper::toDto)
                .orElseThrow(() -> new IllegalArgumentException("The group doesn't exist"));
    }

    @Override
    public GroupDTO delete(Long id) {
        Group toDelete = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("The group doesn't exist"));

        toDelete.setActive(false);

        repository.save(toDelete);

        return mapper.toDto(toDelete);
    }

    @Override
    public GroupDTO update(Long id, GroupForm groupForm) {
        Group toUpdate = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("The group doesn't exist"));

        toUpdate.setName(groupForm.getName());
        toUpdate.setRoleList(groupForm.getRoleList());

        repository.save(toUpdate);

        return mapper.toDto(toUpdate);
    }

    @Override
    public GroupDTO insert(GroupForm groupForm) {
        Group toInsert = mapper.fromFormToEntity(groupForm);

        repository.save(toInsert);

        return mapper.toDto(toInsert);
    }
}
