package com.aronque.foursales.entities.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class TopBuyerDTO {

    private String userId;
    private String username;
    private Long orders;
}
