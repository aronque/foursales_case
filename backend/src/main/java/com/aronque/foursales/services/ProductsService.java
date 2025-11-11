package com.aronque.foursales.services;

import com.aronque.foursales.entities.Product;
import com.aronque.foursales.entities.dto.ProductDTO;

import java.sql.SQLException;
import java.util.List;

public interface ProductsService {

    ProductDTO createNewEntity(ProductDTO obj);

    List<ProductDTO> findAll();

    ProductDTO findById(String productId);

    List<ProductDTO> findByCriteria(Product product);

    ProductDTO updateProduct(String productId, ProductDTO newData);

    void updateStockQty(String productId, int orderItemQty) throws SQLException;

    boolean hasStock(String productId, int orderItemQty);

    void deleteProduct(String productId);
}
