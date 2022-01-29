package com.example.msmailler.rest;

import com.example.msmailler.rest.dto.MailParameter;
import com.example.msmailler.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class MailerRest {
    @Autowired
    EmailService emailService;

    @PostMapping("/send")
    public void sendSimpleMessage(@RequestBody MailParameter mailParameter) {
        emailService.sendSimpleMessage(mailParameter.getTo(), mailParameter.getSubject(), mailParameter.getText());
    }

    @KafkaListener(topics = "mail-topic")
    public void sendMessage(MailParameter mailParameter) {
        System.out.println("Received Message in group foo: " + mailParameter.toString());
        emailService.sendSimpleMessage(mailParameter.getTo(), mailParameter.getSubject(), mailParameter.getText());
    }
}
