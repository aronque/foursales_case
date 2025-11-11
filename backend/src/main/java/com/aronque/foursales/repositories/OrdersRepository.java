package com.aronque.foursales.repositories;

import com.aronque.foursales.entities.Order;
import com.aronque.foursales.entities.User;
import com.aronque.foursales.entities.dto.AverageUserTicketDTO;
import com.aronque.foursales.entities.dto.TopBuyerDTO;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Limit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;

@Repository
public interface OrdersRepository extends JpaRepository<Order, String> {

    @Transactional
    @Modifying
    @Query("UPDATE Order o SET o.status = :status, o.paymentDate = :paymentDate WHERE o.id = :orderId")
    int updateOrderStatus(@Param("orderId") String orderId, @Param("status") Order.OrderStatus status, @Param("paymentDate") Instant date);

    @Query("SELECT o.status FROM Order o WHERE o.id = :orderId")
    Order.OrderStatus findStatusById(String orderId);

    @Query("SELECT o FROM Order o WHERE o.orderUser.id = :userId")
    List<Order> findByUserId(String userId);

    boolean existsByIdAndOrderUser(String id, User orderUser);

    @Query("SELECT new com.aronque.foursales.entities.dto.TopBuyerDTO(u.id, u.username, COUNT(o)) " +
            "FROM Order o " +
            "JOIN o.orderUser u " +
            "GROUP BY u.id, u.username " +
            "ORDER BY COUNT(o) DESC ")
    List<TopBuyerDTO> findTop5Buyers(Limit limit);

    @Query("SELECT new com.aronque.foursales.entities.dto.AverageUserTicketDTO(u.id, u.username, SUM(o.finalPayment)/COUNT(o)) " +
            "FROM Order o " +
            "JOIN o.orderUser u " +
            "WHERE o.orderUser.id = :userId " +
            "GROUP BY u.id, u.username ")
    AverageUserTicketDTO findAverageTicketByUser(String userId);

    @Query("SELECT new com.aronque.foursales.entities.dto.AverageUserTicketDTO(u.id, u.username, SUM(o.finalPayment)/COUNT(o)) " +
            "FROM Order o " +
            "JOIN o.orderUser u " +
            "WHERE o.orderUser.id = u.id " +
            "AND o.status > 0 " +
            "AND o.status < 5" +
            "GROUP BY u.id, u.username ")
    List<AverageUserTicketDTO> findAverageTicketForAllUsers();

    @Query("SELECT SUM(o.finalPayment) " +
            "FROM Order o " +
            "WHERE o.paymentDate >= :initDate " +
            "AND o.paymentDate < :finalDate ")
    Double sumInvoicedOrdersValueByPeriod(@Param("initDate") Instant initDate, @Param("finalDate") Instant finalDate);
}
