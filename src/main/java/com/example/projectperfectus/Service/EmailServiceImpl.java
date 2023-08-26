package com.example.projectperfectus.Service;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.thymeleaf.templateresolver.ITemplateResolver;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

@Service
public class EmailServiceImpl {
    public void sendSimpleEmail(String toEmail,
                                String subject,
                                String body
    ) {
        Properties props = new Properties();

        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");


        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {

                return new PasswordAuthentication("sonede.reclam@gmail.com", "rpvcxksaqpvrltxn");
            }
        });


        try {
            Message message = new MimeMessage(session);

            message.setFrom(new InternetAddress("sonede.reclam@gmail.com"));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
            message.setSubject(subject);
            message.setText(body);

            Transport.send(message);

            System.out.println("Sent");

        } catch (MessagingException e) {
            System.out.println("ERROR: " + e.getMessage());
        }


    }

    @Bean
    public ITemplateResolver thymeleafTemplateResolver() {
        ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
        templateResolver.setPrefix("mail-templates/");
        templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode("HTML");
        templateResolver.setCharacterEncoding("UTF-8");
        return templateResolver;
    }

}

    /*
    public void sendVerificationEmail(String toEmail, String token) {
        String confirmationUrl = "http://localhost:8075/api/verify-account?token=" + token;
        String subject = "Activate your account";
        String body = "Thank you for registering. Please click on the following link to activate your account:\n" + confirmationUrl;
        sendSimpleEmail(toEmail, subject, body);
    }*/

