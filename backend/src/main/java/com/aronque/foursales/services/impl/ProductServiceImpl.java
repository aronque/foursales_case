package com.aronque.foursales.services.impl;

import com.aronque.foursales.entities.Product;
import com.aronque.foursales.entities.dto.ProductDTO;
import com.aronque.foursales.exceptions.ResourceNotFoundException;
import com.aronque.foursales.repositories.ProductsRepository;
import com.aronque.foursales.services.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Component("Product")
public class ProductServiceImpl implements ProductsService {

    @Autowired
    private ProductsRepository repository;

    @Override
    public List<ProductDTO> findAll() {
        return ProductDTO.fromProduct(repository.findAll());
    }

    @Override
    public ProductDTO findById(String productId) {
        try {
            return ProductDTO.fromProduct(repository.findById(productId).get());
        } catch (Exception e) {
            throw new ResourceNotFoundException("Produto não encontrado: [" + productId + "]");
        }
    }

    @Override
    public ProductDTO createNewEntity(ProductDTO obj) {
        return ProductDTO.fromProduct(repository.save(Product.fromDTO(obj)));
    }

    @Override
    public List<ProductDTO> findByCriteria(Product product) {
        return ProductDTO.fromProduct(repository.findAll(Example.of(product)));
    }

    @Override
    public ProductDTO updateProduct(String productId, ProductDTO newData) {
        Optional<Product> opt = repository.findById(productId);

        if(opt.isEmpty()) return null;

        Product actual = opt.get();
        actual.updateFields(newData);

        return ProductDTO.fromProduct(repository.save(actual));
    }

    @Override
    public void updateStockQty(String productId, int orderItemQty) throws SQLException {
        try {
            repository.updateStockQty(productId, orderItemQty);
        } catch (Exception e) {
            throw new SQLException(e.getMessage());
        }
    }

    @Override
    public boolean hasStock(String productId, int orderItemQty) {
        return repository.findStockQtyById(productId) >= orderItemQty;
    }

    @Override
    public void deleteProduct(String productId) {
        if(!repository.existsById(productId)) {
            throw new ResourceNotFoundException("Produto não encontrado.");
        }

        repository.deleteById(productId);
    }
}
