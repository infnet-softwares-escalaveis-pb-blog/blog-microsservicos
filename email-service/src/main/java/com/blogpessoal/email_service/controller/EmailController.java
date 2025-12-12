package com.blogpessoal.email_service.controller;

import com.blogpessoal.email_service.model.EmailRequest;
import com.blogpessoal.email_service.service.EmailService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/emails")
@Tag(name = "Email API", description = "Endpoints para envio e verificação do serviço de e-mail")
public class EmailController {

    @Autowired
    private EmailService emailService;

    @Operation(
            summary = "Enviar um e-mail",
            description = "Recebe os dados do e-mail e executa o envio para o destinatário informado."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "E-mail enviado com sucesso."),
            @ApiResponse(responseCode = "400", description = "Dados inválidos."),
            @ApiResponse(responseCode = "500", description = "Erro interno ao enviar e-mail.")
    })
    @PostMapping("/send")
    public ResponseEntity<Map<String, Object>> sendEmail(@RequestBody EmailRequest request) {
        emailService.sendEmail(request);

        Map<String, Object> response = new HashMap<>();
        response.put("status", "success");
        response.put("message", "E-mail enviado com sucesso!");
        response.put("to", request.getTo());

        return ResponseEntity.ok(response);
    }

    @Operation(
            summary = "Verificação se o serviço está ativo",
            description = "Verifica se o serviço de e-mail está ativo."
    )
    @ApiResponse(responseCode = "200", description = "Serviço funcionando corretamente.")
    @GetMapping("/ping")
    public String ping() {
        return "Email Service ativo e funcionando!";
    }
}
