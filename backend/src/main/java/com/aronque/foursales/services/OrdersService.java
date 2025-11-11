package com.aronque.foursales.services;

import com.aronque.foursales.entities.Order;
import com.aronque.foursales.entities.dto.AverageUserTicketDTO;
import com.aronque.foursales.entities.dto.MonthlyTotalInvoicedDTO;
import com.aronque.foursales.entities.dto.OrderDTO;
import com.aronque.foursales.entities.dto.TopBuyerDTO;
import com.aronque.foursales.exceptions.InvalidAttributeException;

import java.time.Month;
import java.util.List;

public interface OrdersService {

    OrderDTO createNewOrder(OrderDTO obj, String userId);

    List<OrderDTO> findAll(String userId);

    OrderDTO getOrderById(String orderId, String userId);

    boolean exists(String orderId);

    boolean isFromUser(String orderId, String userId);

    void confirmPayment(String orderId) throws InvalidAttributeException;

    Order.OrderStatus findOrderStatus(String orderId);

    List<TopBuyerDTO> findTopBuyerUsers();

    AverageUserTicketDTO findAverageTicketByUser(String userId);

    List<AverageUserTicketDTO> findAverageTicketForAllUsers();

    MonthlyTotalInvoicedDTO findMonthlyTotalInvoiced(Month month, int year);
}
