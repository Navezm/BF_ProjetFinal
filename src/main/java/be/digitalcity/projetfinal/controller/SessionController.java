package be.digitalcity.projetfinal.controller;

import be.digitalcity.projetfinal.services.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SessionController {

    private final SessionService service;

    @Autowired
    public SessionController(SessionService service) {
        this.service = service;
    }
}
