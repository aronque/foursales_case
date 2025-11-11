package com.aronque.foursales.services;

import com.aronque.foursales.entities.dto.AverageUserTicketDTO;
import com.aronque.foursales.entities.dto.MonthlyTotalInvoicedDTO;
import com.aronque.foursales.entities.dto.TopBuyerDTO;
import org.springframework.stereotype.Service;

import java.time.Month;
import java.util.List;

@Service
public interface AdminQueriesService {

    List<TopBuyerDTO> getTopBuyers();

    AverageUserTicketDTO getAverageTicketByUser(String userId);

    List<AverageUserTicketDTO> getAverageTickets();

    MonthlyTotalInvoicedDTO getTotalAmountInvoicedByMonth(Month month, int year);
}
