package be.digitalcity.projetfinal.services;

import be.digitalcity.projetfinal.models.dto.UserDTO;
import be.digitalcity.projetfinal.models.form.userForm.UserRegisterForm;
import be.digitalcity.projetfinal.models.form.userForm.UserUpdateForm;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
    List<UserDTO> findAll();

    UserDTO getOne(Long id);

    UserDTO delete(Long id);

    UserDTO update(Long id, UserUpdateForm userUpdateForm);

    UserDTO insert(UserRegisterForm userRegisterForm);

    List<UserDTO> findByGroup(Long id);
}
