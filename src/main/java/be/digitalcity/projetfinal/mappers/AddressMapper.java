package be.digitalcity.projetfinal.mappers;

import be.digitalcity.projetfinal.models.dto.AddressDTO;
import be.digitalcity.projetfinal.models.entity.Address;
import be.digitalcity.projetfinal.models.form.AddressForm;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AddressMapper {
    AddressDTO toDto(Address address);

    Address toEntity(AddressDTO addressDTO);

    Address fromFormToEntity(AddressForm addressForm);
}
