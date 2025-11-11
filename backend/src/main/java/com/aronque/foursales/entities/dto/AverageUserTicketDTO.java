package com.aronque.foursales.entities.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class AverageUserTicketDTO {

    private String userId;
    private String username;
    private Double averageTicket;
}
