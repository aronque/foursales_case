package com.aronque.foursales.controllers;

import com.aronque.foursales.entities.dto.OrderDTO;
import com.aronque.foursales.services.OrdersService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/orders")
@Tag(name = "Pedidos", description = "Endpoints para gerenciamento de pedidos")
public class OrderController {

    @Autowired
    @Qualifier("Order")
    private OrdersService service;


    @GetMapping("/all")
    public ResponseEntity getAllOrders(JwtAuthenticationToken token) {

        return ResponseEntity.ok(service.findAll(token.getName()));
    }


    @Operation(summary = "Cria um novo pedido",
            description = "Cria um novo pedido associado a um usuário e seus itens, para usuários comuns.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Pedido criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos - itens vazios"),
            @ApiResponse(responseCode = "404", description = "Recurso não encontrado - Produto não existe")
    })
    @PostMapping("/create")
    public ResponseEntity createOrder(@Valid @RequestBody OrderDTO order, JwtAuthenticationToken token) {

        return ResponseEntity.status(HttpStatus.CREATED).body(service.createNewOrder(order, token.getName()));
    }


    @Operation(summary = "Busca um pedido pelo ID",
            description = "Busca um pedido pelo ID, sendo necessário o usuário do pedido estar autenticado.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pedido encontrado"),
            @ApiResponse(responseCode = "404", description = "Recurso não encontrado - Pedido não existe/Usuário não autorizado")
    })
    @GetMapping("/{id}")
    public ResponseEntity getOrderById(@PathVariable String id, JwtAuthenticationToken token) {

        return ResponseEntity.ok(service.getOrderById(id, token.getName()));
    }
}
