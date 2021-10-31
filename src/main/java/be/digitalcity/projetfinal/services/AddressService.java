package be.digitalcity.projetfinal.services;

import be.digitalcity.projetfinal.models.dto.AddressDTO;
import be.digitalcity.projetfinal.models.form.AddressForm;

import java.util.List;

public interface AddressService {
    List<AddressDTO> findAll();

    AddressDTO getOne(Long id);

    AddressDTO delete(Long id);

    AddressDTO update(Long id, AddressForm addressForm);

    AddressDTO insert(AddressForm addressForm);
}
