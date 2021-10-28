package be.digitalcity.projetfinal.models.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Set;

@Getter
@Setter
@EqualsAndHashCode
@ToString
public class GroupDTO {
    private Long id;
    private String name;
    private Set<RoleDTO> roleList;
}
