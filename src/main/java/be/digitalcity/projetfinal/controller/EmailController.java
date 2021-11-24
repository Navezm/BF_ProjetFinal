package be.digitalcity.projetfinal.controller;

import be.digitalcity.projetfinal.models.form.EmailForm;
import be.digitalcity.projetfinal.services.impl.EmailService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class EmailController {
    private final EmailService service;

    public EmailController(EmailService service) {
        this.service = service;
    }

    @PostMapping("/sendMail")
    public ResponseEntity<String> sendMail(@RequestBody @Valid EmailForm email){
        this.service.sendEmail(email);

        return ResponseEntity.ok().body("Mail send");
    }
}
