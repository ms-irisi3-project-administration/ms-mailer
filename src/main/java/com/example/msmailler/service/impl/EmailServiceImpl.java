package com.example.msmailler.service.impl;

import com.example.msmailler.rest.dto.MailParameter;
import com.example.msmailler.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl  implements EmailService {
    @Autowired
    private JavaMailSender emailSender;

    @Value("${kafka.topic.mail}")
    String topicName;

    @Override
    public void sendSimpleMessage(String to, String subject, String text) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom("futursCours@localhost.com");
            message.setTo(to);
            message.setSubject(subject);
            message.setText(text);
            emailSender.send(message);
        }
        catch (Exception e) {
            System.out.println("Erreur lors de l'envoi du mail");
            e.printStackTrace();
        }
    }
}
