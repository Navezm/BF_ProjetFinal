package be.digitalcity.projetfinal.services.impl;

import be.digitalcity.projetfinal.mappers.AddressMapper;
import be.digitalcity.projetfinal.models.dto.AddressDTO;
import be.digitalcity.projetfinal.models.entity.Address;
import be.digitalcity.projetfinal.models.form.AddressForm;
import be.digitalcity.projetfinal.repository.AddressRepository;
import be.digitalcity.projetfinal.services.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AddressServiceImpl implements AddressService {
    private final AddressRepository repository;
    private final AddressMapper mapper;

    @Autowired
    public AddressServiceImpl(AddressRepository repository, AddressMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public List<AddressDTO> findAll() {
        return repository.findAll()
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public AddressDTO getOne(Long id) {
        return repository.findById(id)
                .map(mapper::toDto)
                .orElseThrow(() -> new IllegalArgumentException("This address does not exist"));
    }

    @Override
    public AddressDTO delete(Long id) {
        Address toDelete = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("This address does not exist"));

        repository.delete(toDelete);

        return mapper.toDto(toDelete);
    }

    @Override
    public AddressDTO update(Long id, AddressForm addressForm) {
        Address toUpdate = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("This address does not exist"));

        toUpdate.setStreet(addressForm.getStreet());
        toUpdate.setCity(addressForm.getCity());
        toUpdate.setNumber(addressForm.getNumber());
        toUpdate.setPostCode(addressForm.getPostCode());

        repository.save(toUpdate);

        return mapper.toDto(toUpdate);
    }

    @Override
    public AddressDTO insert(AddressForm addressForm) {
        Address toInsert = mapper.fromFormToEntity(addressForm);

        repository.save(toInsert);

        return mapper.toDto(toInsert);
    }
}
