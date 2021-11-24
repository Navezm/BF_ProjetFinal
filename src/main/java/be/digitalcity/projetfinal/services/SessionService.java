package be.digitalcity.projetfinal.services;

import be.digitalcity.projetfinal.models.dto.UserDTO;
import be.digitalcity.projetfinal.models.form.userForm.UserLoginForm;
import be.digitalcity.projetfinal.models.form.userForm.UserRegisterForm;

public interface SessionService {

    UserDTO login(UserLoginForm form);
    UserDTO register(UserRegisterForm form);

}
