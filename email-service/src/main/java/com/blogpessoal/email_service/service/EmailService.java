package com.blogpessoal.email_service.service;

import com.blogpessoal.email_service.model.EmailRequest;
import com.blogpessoal.email_service.exception.EmailException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private static final Logger logger = LoggerFactory.getLogger(EmailService.class);

    @Autowired
    private JavaMailSender mailSender;

    public void sendEmail(EmailRequest request) {
        try {
            validateEmailRequest(request);

            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(request.getTo());
            message.setSubject(request.getSubject());
            message.setText(request.getBody());

            mailSender.send(message);
            logger.info("E-mail enviado com sucesso para {}", request.getTo());

        } catch (Exception e) {
            logger.error("Erro ao enviar e-mail para {}", request.getTo(), e);
            throw new EmailException("Erro ao enviar e-mail para " + request.getTo(), e);
        }
    }

    private void validateEmailRequest(EmailRequest request) {
        if (request.getTo() == null || request.getTo().isEmpty()) {
            throw new EmailException("O campo 'to' (destinatário) é obrigatório.");
        }
        if (request.getSubject() == null || request.getSubject().isEmpty()) {
            throw new EmailException("O campo 'subject' é obrigatório.");
        }
        if (request.getBody() == null || request.getBody().isEmpty()) {
            throw new EmailException("O campo 'body' é obrigatório.");
        }
    }
}
