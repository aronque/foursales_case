package com.aronque.foursales.services.impl;

import com.aronque.foursales.entities.dto.AverageUserTicketDTO;
import com.aronque.foursales.entities.dto.MonthlyTotalInvoicedDTO;
import com.aronque.foursales.entities.dto.TopBuyerDTO;
import com.aronque.foursales.services.AdminQueriesService;
import com.aronque.foursales.services.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Month;
import java.util.List;

@Component("AdminQueries")
public class AdminQueriesServiceImpl implements AdminQueriesService {

    @Autowired
    private OrdersService ordersService;

    @Override
    public List<TopBuyerDTO> getTopBuyers() {
        return ordersService.findTopBuyerUsers();
    }

    @Override
    public AverageUserTicketDTO getAverageTicketByUser(String userId) {
        return ordersService.findAverageTicketByUser(userId);
    }

    @Override
    public List<AverageUserTicketDTO> getAverageTickets() {
        return ordersService.findAverageTicketForAllUsers();
    }

    @Override
    public MonthlyTotalInvoicedDTO getTotalAmountInvoicedByMonth(Month month, int year) {
        return ordersService.findMonthlyTotalInvoiced(month, year);
    }
}
