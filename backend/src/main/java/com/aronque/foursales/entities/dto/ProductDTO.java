package com.aronque.foursales.entities.dto;

import com.aronque.foursales.entities.Product;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class ProductDTO {

    private String id;

    @NotNull(message = "O produto deve ter um nome.")
    private String name;

    private String description;

    @NotNull(message = "O produto deve ter um preço.")
    private Double price;

    @NotNull(message = "O produto deve ter alguma quantidade.")
    private Integer qty;

    private Product.Category category;

    public static ProductDTO fromProduct(Product product) {
        return new ProductDTO(product.getId(), product.getName(), product.getDescription(), product.getPrice(), product.getStockQty(), product.getCategory());
    }

    public static List<ProductDTO> fromProduct(List<Product> product) {
        return product.stream().map(ProductDTO::fromProduct).toList();
    }
}
