package com.example.blog.service;

import com.example.blog.domain.Post;
import com.example.blog.dto.EmailNotificationRequest;
import com.example.blog.integration.EmailServiceClient;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class EmailNotificationService {

    private static final Logger logger = LoggerFactory.getLogger(EmailNotificationService.class);
    
    private final EmailServiceClient emailServiceClient;

    public EmailNotificationService(EmailServiceClient emailServiceClient) {
        this.emailServiceClient = emailServiceClient;
    }

    public void notifyPostCreated(Post post) {
        try {
            String subject = "Novo Post Criado: " + post.getTitle();
            String body = buildEmailBody(post);
            
            EmailNotificationRequest emailRequest = new EmailNotificationRequest(
                post.getAuthor().getEmail(),
                subject,
                body
            );

            var response = emailServiceClient.sendEmail(emailRequest);
            logger.info("Email enviado com sucesso para {}: {}", 
                       post.getAuthor().getEmail(), response);
                       
        } catch (Exception e) {
            logger.error("Erro ao enviar email para {}: {}", 
                        post.getAuthor().getEmail(), e.getMessage());
        }
    }

    private String buildEmailBody(Post post) {
        return String.format("""
            Olá %s!
            
            Seu post "%s" foi criado com sucesso!
            
            Slug: %s
            Status: %s
            Categoria: %s
            
            Conteúdo:
            %s
            
            Atenciosamente,
            Equipe do Blog
            """, 
            post.getAuthor().getName(),
            post.getTitle(),
            post.getSlug(),
            post.getStatus(),
            post.getCategory().getName(),
            post.getContent().length() > 200 ? 
                post.getContent().substring(0, 200) + "..." : 
                post.getContent()
        );
    }
}