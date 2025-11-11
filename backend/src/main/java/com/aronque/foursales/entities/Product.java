package com.aronque.foursales.entities;

import com.aronque.foursales.entities.dto.ProductDTO;
import com.aronque.foursales.exceptions.InvalidAttributeException;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Check;

import java.io.Serial;
import java.time.Instant;

@Entity
@Getter
@Setter
@Table(name = "PRODUCTS")
@Check(constraints = "stock_qty >= 0")
@AllArgsConstructor
public class Product {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @NotNull
    @Column(nullable = false)
    private String name;

    @Column
    private String description;

    @Enumerated(EnumType.ORDINAL)
    @Column
    private Category category;

    @NotNull
    @Column(nullable = false)
    private Double price;

    @NotNull
    @Column
    private Integer stockQty;

    @Column(updatable = false)
    private Instant creationDate;

    @Column
    private Instant attDate;

    public Product() {
        creationDate = Instant.now();
    }

    public enum Category {
        KITCHEN (0),
        LIVING_ROOM (1),
        BATHROOM(2),
        DECORATION(3);

        public final int intValue;

        Category() {
            this.intValue = 0;
        }

        Category(int value) {
            this.intValue = value;
        }
    }

    public void updateFields(ProductDTO newData) {

        if(newData.getName() != null &&
                !newData.getName().equals(this.getName())) {
            this.setName(newData.getName());
        }

        if(newData.getDescription() != null &&
                !newData.getDescription().equals(this.getDescription())) {
            this.setDescription(newData.getDescription());
        }

        if(newData.getPrice() != null &&
                !newData.getPrice().equals(this.getPrice())) {

            if(newData.getPrice() < 0.0) {
                throw new InvalidAttributeException("O preço do produto não pode ser negativo.");
            }

            this.setPrice(newData.getPrice());
        }

        if(newData.getQty() != null &&
                !newData.getQty().equals(this.getStockQty())) {

            if(newData.getQty() < 0) {
                throw new InvalidAttributeException("A quantidade em estoque não pode ser negativa.");
            }

            this.setStockQty(newData.getQty());
        }

        this.setAttDate(Instant.now());
    }

    public static Product fromDTO(ProductDTO dto) {
        Product instance = new Product();
        instance.setId(dto.getId());
        instance.setName(dto.getName());
        instance.setDescription(dto.getDescription());
        instance.setCategory(dto.getCategory());
        instance.setPrice(dto.getPrice());
        instance.setStockQty(dto.getQty());

        return instance;
    }
}