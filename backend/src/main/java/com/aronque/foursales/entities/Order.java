package com.aronque.foursales.entities;

import com.aronque.foursales.entities.dto.OrderDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "ORDERS")
@Getter
@Setter
@AllArgsConstructor
public class Order {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @NotEmpty(message = "O pedido deve conter pelo menos um item.")
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "order", orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<OrderItem> items = new HashSet<>();

    @Column
    private OrderStatus status;

    @ManyToOne
    @JoinColumn(name = "order_user_id")
    @JsonIgnoreProperties({"password", "role", "orders"})
    private User orderUser;

    @Column(insertable = false)
    private Double finalPayment;

    @Column(updatable = false)
    private Instant creationDate;

    @Column(insertable = false)
    private Instant paymentDate;

    public Order() {
        status = OrderStatus.PENDING_PAYMENT;
        creationDate = Instant.now();
    }

    public enum OrderStatus {
        PENDING_PAYMENT (0),
        IN_PROCESS (1),
        PROCESSED(2),
        SHIPPED(3),
        CONCLUDED(4),
        CANCELED(5);

        public final int intValue;

        OrderStatus() {
            this.intValue = 0;
        }

        OrderStatus(int value) {
            this.intValue = value;
        }
    }

    @JsonProperty
    public Double getTotalPrice() {
        return items.stream()
                .mapToDouble(OrderItem::getTotalPrice)
                .sum();
    }

    public static Order fromDTO(OrderDTO dto) {
        Order instance = new Order();
        instance.setItems(OrderItem.fromDTO(dto.getItems()));

        return instance;
    }

}
