package be.digitalcity.projetfinal.models.dto;

import lombok.*;

@Getter
@Setter
@EqualsAndHashCode
@ToString
public class AddressDTO {
    private Long id;
    private String street;
    private String number;
    private String postCode;
    private String city;
}
