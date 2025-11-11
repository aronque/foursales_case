package com.aronque.foursales.entities.dto;

import com.aronque.foursales.entities.Order;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
public class OrderDTO {

    private String id;

    @NotEmpty(message = "O pedido deve conter pelo menos um item.")
    private Set<OrderItemDTO> items;
    private Order.OrderStatus status;
    private Double totalPrice;
    private UserDTO userDTO;

    public static OrderDTO fromOrder(Order order) {
        return new OrderDTO(
                order.getId(),
                OrderItemDTO.fromOrderItem(order.getItems()),
                order.getStatus(),
                order.getTotalPrice(),
                UserDTO.fromUser(order.getOrderUser()));
    }

    public static List<OrderDTO> fromOrder(List<Order> orders) {
        return orders.stream().map(OrderDTO::fromOrder).toList();
    }

}
