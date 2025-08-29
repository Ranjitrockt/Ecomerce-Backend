package com.ecomerce_backend.ecomerce_backend.ServiceImplement;

import com.ecomerce_backend.ecomerce_backend.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpt implements EmailService {


        @Autowired
        private JavaMailSender mailSender;

        public void sendEmail(String to, String subject, String body) {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom("ranjitrock9540.com"); // Replace with your actual email
            message.setTo(to);
            message.setSubject(subject);
            message.setText(body);

            mailSender.send(message);

    }

}
