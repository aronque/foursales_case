package com.aronque.foursales.repositories;

import com.aronque.foursales.entities.Product;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductsRepository extends JpaRepository<Product, String> {

    @Transactional
    @Modifying
    @Query("update Product p set p.stockQty = p.stockQty - :qty where p.id = :productId")
    int updateStockQty(@Param("productId") String productId, @Param("qty") int newQty);

    @Query("select p.stockQty from Product p where p.id = :productId ")
    Integer findStockQtyById(String productId);
}
