package com.bookbouqet.service;

import com.bookbouqet.enumaration.EmailTemplateName;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender mailSender;
   private final SpringTemplateEngine templateEngine;



   @Async
   public void sendEmail(String to,
                         String username,
                         EmailTemplateName emailTemplate,
                         String confirmationURL,
                         String activationCode,
                         String subject) throws MessagingException {


       String emailTemplateName = Objects.requireNonNullElse(emailTemplate, EmailTemplateName.CONFIRM_EMAIL).getName();
       MimeMessage mimeMessage = mailSender.createMimeMessage();
       MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(
               mimeMessage,
               MimeMessageHelper.MULTIPART_MODE_MIXED,
               StandardCharsets.UTF_8.name());

       Map<String, Object> properties = new HashMap<>();
       properties.put("username", username);
       properties.put("confirmation_url", confirmationURL);
       properties.put("activation_code", activationCode);

       Context context = new Context();
       context.setVariables(properties);

       mimeMessageHelper.setFrom("contact@bookbouqet.com");
       mimeMessageHelper.setTo(to);
       mimeMessageHelper.setSubject(subject);

       String template = templateEngine.process(emailTemplateName, context);
       mimeMessageHelper.setText(template, true);
       mailSender.send(mimeMessage);
    }
}
