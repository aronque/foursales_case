package com.aronque.foursales.controllers;

import com.aronque.foursales.services.AdminQueriesService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Month;

@RestController
@RequestMapping("/admin/queries")
@Tag(name = "Consultas Admin", description = "Endpoints para consultas do usuário administrador")
public class AdminQueriesController {

    @Autowired
    private AdminQueriesService service;


    @Operation(summary = "Listar 5 maiores compradores")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Top 5 usuários listados"),
    })
    @GetMapping("/topBuyers")
    public ResponseEntity getTopBuyers() {
        return ResponseEntity.ok(service.getTopBuyers());
    }


    @Operation(summary = "Ticket Médio por usuário")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ticket Médio calculado"),
    })
    @GetMapping("/averageTicket/{userId}")
    public ResponseEntity getAverageTicketByUser(@PathVariable String userId) {

        return ResponseEntity.ok(service.getAverageTicketByUser(userId));
    }


    @Operation(summary = "Ticket Médio de todos usuários")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tickets Médio calculados"),
    })
    @GetMapping("/averageTicket/all")
    public ResponseEntity getAverageTicketAllUsers() {

        return ResponseEntity.ok(service.getAverageTickets());
    }


    @Operation(summary = "Valor total faturado no mês")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Valor total calculado para o mês e ano"),
    })
    @GetMapping("/totalAmountInvoicedByMonth")
    public ResponseEntity getTotalAmountInvoicedByMonth(@RequestParam("month") Month month, int year) {

        return ResponseEntity.ok(service.getTotalAmountInvoicedByMonth(month, year));
    }
}
