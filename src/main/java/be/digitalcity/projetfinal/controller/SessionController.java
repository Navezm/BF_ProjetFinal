package be.digitalcity.projetfinal.controller;

import static be.digitalcity.projetfinal.config.SecurityConstants.*;

import be.digitalcity.projetfinal.models.dto.UserDTO;
import be.digitalcity.projetfinal.models.form.userForm.UserLoginForm;
import be.digitalcity.projetfinal.models.form.userForm.UserRegisterForm;
import be.digitalcity.projetfinal.services.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SessionController {

    private final SessionService service;

    @Autowired
    public SessionController(SessionService service) {
        this.service = service;
    }

    @PostMapping(LOGIN_URL)
    public ResponseEntity<UserDTO> login(@RequestBody UserLoginForm form){
        return ResponseEntity.ok(service.login(form));
    }

    @PostMapping(REGISTER_URL)
    public ResponseEntity<UserDTO> register(@RequestBody UserRegisterForm form){
        return ResponseEntity.ok(service.register(form));
    }
}
