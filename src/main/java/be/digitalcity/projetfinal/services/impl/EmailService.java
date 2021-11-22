package be.digitalcity.projetfinal.services.impl;

import be.digitalcity.projetfinal.models.form.EmailForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private final JavaMailSender javaMailSender;

    @Autowired
    public EmailService(JavaMailSender javaMailSender){
        this.javaMailSender = javaMailSender;
    }

    public void sendEmail(EmailForm email) throws MailException {
        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo("testspringophelie@gmail.com");
        mail.setFrom("testspringophelie@gmail.com");
        mail.setSubject("New contact");
        mail.setText("Message from: " + email.getName() + " (email address: " + email.getEmail() + ").\n\nContent: " + email.getMessage());

        javaMailSender.send(mail);
        System.out.println("email sent");
    }

}
