package com.aronque.foursales.services.impl;

import com.aronque.foursales.entities.Order;
import com.aronque.foursales.exceptions.InvalidAttributeException;
import com.aronque.foursales.exceptions.ResourceNotFoundException;
import com.aronque.foursales.services.OrdersService;
import com.aronque.foursales.services.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.SQLException;

@Component("Payment")
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private OrdersService ordersService;

    @Override
    public void confirm(String orderId, String userId) throws InvalidAttributeException, ResourceNotFoundException {

        if(!ordersService.exists(orderId)) {
            throw new ResourceNotFoundException("Pedido inexistente.");
        }

        if(!ordersService.isFromUser(orderId, userId)) {
            throw new ResourceNotFoundException("Pedido não encontrado.");
        }

        if(!ordersService.findOrderStatus(orderId).equals(Order.OrderStatus.PENDING_PAYMENT)) {
            throw new InvalidAttributeException("Pedido já foi pago.");
        }

        ordersService.confirmPayment(orderId);
    }
}
