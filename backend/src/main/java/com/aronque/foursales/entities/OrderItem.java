package com.aronque.foursales.entities;

import com.aronque.foursales.entities.dto.OrderItemDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Getter
@Setter
@Table(name = "ORDER_ITEM")
@AllArgsConstructor
@NoArgsConstructor
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @JsonIgnore
    @ManyToOne
    private Order order;

    @ManyToOne
    private Product product;

    @Column
    private Integer qty;

    public Double getTotalPrice() {
        return product.getPrice() * qty;
    }

    public static OrderItem fromDTO(OrderItemDTO dto) {
        return new OrderItem(null, null, Product.fromDTO(dto.getProduct()), dto.getQty());
    }

    public static Set<OrderItem> fromDTO(Set<OrderItemDTO> dtos) {
        return dtos.stream().map(OrderItem::fromDTO).collect(Collectors.toSet());
    }
}
