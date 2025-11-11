package com.aronque.foursales.entities.dto;

import com.aronque.foursales.entities.OrderItem;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
public class OrderItemDTO {

    @JsonIgnoreProperties({"qty", "description"})
    private ProductDTO product;
    private Integer qty;
    private Double totalPrice;

    public static OrderItemDTO fromOrderItem(OrderItem orderItem) {
        return new OrderItemDTO(
                ProductDTO.fromProduct(orderItem.getProduct()),
                orderItem.getQty(),
                orderItem.getTotalPrice());
    }

    public static Set<OrderItemDTO> fromOrderItem(Set<OrderItem> orderItem) {
        return orderItem.stream().map(OrderItemDTO::fromOrderItem).collect(Collectors.toSet());
    }
}
