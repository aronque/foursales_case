package com.aronque.foursales.services.impl;

import com.aronque.foursales.entities.Order;
import com.aronque.foursales.entities.OrderItem;
import com.aronque.foursales.entities.dto.*;
import com.aronque.foursales.exceptions.InvalidAttributeException;
import com.aronque.foursales.exceptions.ResourceNotFoundException;
import com.aronque.foursales.repositories.OrderItemRepository;
import com.aronque.foursales.repositories.OrdersRepository;
import com.aronque.foursales.services.OrdersService;
import com.aronque.foursales.services.ProductsService;
import com.aronque.foursales.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Limit;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.time.*;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Component("Order")
public class OrderServiceImpl implements OrdersService {

    @Autowired
    private OrdersRepository repository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private ProductsService productsService;

    @Autowired
    private UsersService usersService;

    @Override
    public OrderDTO createNewOrder(OrderDTO obj, String userId) {

        Order parsedOrder = Order.fromDTO(obj);

        for(OrderItem item : parsedOrder.getItems()) {

            ProductDTO product = productsService.findById(item.getProduct().getId());

            if(product.getQty() < item.getQty()) {
                throw new InvalidAttributeException("Quantidade solicitada do produto ID [" + item.getProduct().getId() + "] indisponível no estoque.");
            }

            item.setOrder(parsedOrder);

            //tratamento para devolver na mesma operacao os dados dos produtos associados ao pedido
            item.getProduct().setPrice(product.getPrice());
            item.getProduct().setName(product.getName());
            item.getProduct().setCategory(product.getCategory());
        }

        parsedOrder.setOrderUser(usersService.findUserById(userId));

        return OrderDTO.fromOrder(repository.save(parsedOrder));
    }

    @Override
    public List<OrderDTO> findAll(String userId) {
        return OrderDTO.fromOrder(repository.findByUserId(userId));
    }

    @Override
    public OrderDTO getOrderById(String orderId, String userId) {
        Optional<Order> opt = repository.findById(orderId);
        if(opt.isEmpty()) {
            throw new ResourceNotFoundException("Pedido não encontrado.");
        }

        Order order = opt.get();

        if(!order.getOrderUser().getId().equals(userId)) {
            throw new ResourceNotFoundException("Pedido não encontrado.");
        }

        return OrderDTO.fromOrder(order);
    }

    @Override
    public boolean exists(String orderId) {
        return repository.existsById(orderId);
    }

    @Override
    public boolean isFromUser(String orderId, String userId) {
        return repository.existsByIdAndOrderUser(orderId, usersService.findReferenceById(userId));
    }

    @Transactional
    @Override
    public void confirmPayment(String orderId) throws InvalidAttributeException {
        Set<OrderItem> orderItems = orderItemRepository.findByOrderId(orderId);

        try {
            for(OrderItem item : orderItems) {
                productsService.updateStockQty(item.getProduct().getId(), item.getQty());
            }
        } catch(SQLException e) {
            throw new InvalidAttributeException("Quantidade de produto selecionado indisponível. O pedido será cancelado.");
        } finally {
            repository.updateOrderStatus(orderId, Order.OrderStatus.CANCELED, null);
        }

        Order order = repository.findById(orderId).get();
        order.setStatus(Order.OrderStatus.IN_PROCESS);
        order.setPaymentDate(Instant.now());
        order.setFinalPayment(order.getTotalPrice());

        repository.save(order);
    }

    @Override
    public Order.OrderStatus findOrderStatus(String orderId) {
        return repository.findStatusById(orderId);
    }

    @Override
    public List<TopBuyerDTO> findTopBuyerUsers() {
        return repository.findTop5Buyers(Limit.of(5));
    }

    @Override
    public AverageUserTicketDTO findAverageTicketByUser(String userId) {
        return repository.findAverageTicketByUser(userId);
    }

    @Override
    public List<AverageUserTicketDTO> findAverageTicketForAllUsers() {
        return repository.findAverageTicketForAllUsers();
    }

    @Override
    public MonthlyTotalInvoicedDTO findMonthlyTotalInvoiced(Month month, int year) {

        LocalDate firstMonthDay = YearMonth.of(year, month).atDay(1);
        LocalDate lastMonthDay = firstMonthDay.plusMonths(1);

        Instant initMonthInstant = firstMonthDay.atStartOfDay(ZoneId.systemDefault()).toInstant();
        Instant initNextMonthInstant = lastMonthDay.atStartOfDay(ZoneId.systemDefault()).toInstant();

        return new MonthlyTotalInvoicedDTO(month, repository.sumInvoicedOrdersValueByPeriod(initMonthInstant, initNextMonthInstant), year);
    }
}
