package com.aronque.foursales.entities.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.Month;

@Getter
@Setter
@AllArgsConstructor
public class MonthlyTotalInvoicedDTO {

    private Month month;
    private int year;
    private Double totalValue;

    public MonthlyTotalInvoicedDTO(Month month, Double totalValue, int year) {
        this.month = month;
        this.totalValue = totalValue != null ? totalValue : 0.0;
        this.year = year;
    }
}
