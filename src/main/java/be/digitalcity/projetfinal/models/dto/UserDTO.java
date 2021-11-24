package be.digitalcity.projetfinal.models.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
@ToString
public class UserDTO {
    private Long id;
    private String username;
    private String email;
    private List<RoleDTO> roles;
    private GroupDTO group;
    private AddressDTO address;

    private String token;
    private boolean accountNonExpired;
    private boolean accountNonLocked;
    private boolean creditialsNonExpired;
    private boolean enabled;
}
