package dev.tanaka.spring_security.service;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@Async
public class EmailSenderService {
    private final JavaMailSender emailSender;


    public EmailSenderService(JavaMailSender emailSender) {
        this.emailSender = emailSender;
    }

    public void sendEmail(String to,String token){
        try{
            SimpleMailMessage emailTemplate = new SimpleMailMessage();
            emailTemplate.setTo(to);
            emailTemplate.setFrom("tanakaaugustine@gmail.com");
            emailTemplate.setSubject("Confirm your email address");
            String messageBody =
                    """

                    Hello from Dev.Tanaka App Team!
                    Please use the following link to verify your email:

                    http://localhost:8080/confirm-token?token=%s
                    """.formatted(token);
            emailTemplate.setText(messageBody);
            emailSender.send(emailTemplate);

        }catch (Exception e) {
            e.printStackTrace();        }


    }
}
