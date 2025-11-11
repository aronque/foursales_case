package com.aronque.foursales.repositories;

import com.aronque.foursales.entities.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, String>  {

    Set<OrderItem> findByOrderId(String orderId);
}
