package com.aronque.foursales.controllers;

import com.aronque.foursales.entities.dto.DefaultMessageDTO;
import com.aronque.foursales.services.PaymentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/orders/{orderId}/payment")
@Tag(name = "Pagamento", description = "Endpoints para pagamento de pedidos")
public class PaymentController {

    @Autowired
    private PaymentService service;


    @Operation(summary = "Confirma o pagamento do pedido")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pagamento realizado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Credenciais inválidas, " +
                    "quantidade insuficiente de produtos no estoque - cancelamento de pedido"),
            @ApiResponse(responseCode = "404", description = "Recurso não encontrado - Pedido inexistente")
    })
    @PostMapping("/confirm")
    public ResponseEntity confirmPayment(@PathVariable String orderId, JwtAuthenticationToken token) {

        service.confirm(orderId, token.getName());
        return ResponseEntity.ok(new DefaultMessageDTO("Pagamento confirmado com sucesso."));
    }
}
