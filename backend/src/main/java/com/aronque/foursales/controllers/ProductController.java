package com.aronque.foursales.controllers;

import com.aronque.foursales.entities.Product;
import com.aronque.foursales.entities.dto.DefaultMessageDTO;
import com.aronque.foursales.entities.dto.ProductDTO;
import com.aronque.foursales.services.ProductsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@Tag(name = "Produtos", description = "Endpoints para gerenciamento de produtos")
public class ProductController {

    @Autowired
    @Qualifier("Product")
    private ProductsService service;


    @Operation(summary = "Lista todos produtos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Produtos listados")
    })
    @GetMapping("/all")
    public ResponseEntity findAllProducts() {
        List<ProductDTO> response = service.findAll();

        return ResponseEntity.ok(response);
    }


    @Operation(summary = "Lista as categorias de produtos",
                description = "Lista as categorias de produtos disponíveis para criação de novos objetos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Categorias listadas"),
    })
    @GetMapping("/categories")
    public ResponseEntity findCategories() {
        return ResponseEntity.ok(Product.Category.values());
    }


    @Operation(summary = "Cria um produto")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Produto criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Quantidade de estoque inválida, preço inválido"),
    })
    @PostMapping("/create")
    public ResponseEntity createNewProduct(@Valid @RequestBody ProductDTO product) {

        ProductDTO response = service.createNewEntity(product);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }


    @Operation(summary = "Atualiza um produto")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Produto atualizado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Quantidade de estoque inválida, preço inválido"),
    })
    @PutMapping("/{id}")
    public ResponseEntity updateProduct(@PathVariable("id") String productId, @RequestBody ProductDTO product) {

        ProductDTO response = service.updateProduct(productId, product);

        return ResponseEntity.ok(response);
    }


    @Operation(summary = "Deleta um produto")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Produto deletado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Produto não encontrado"),
    })
    @DeleteMapping("/{id}")
    public ResponseEntity deleteProduct(@PathVariable("id") String productId) {

        service.deleteProduct(productId);

        return ResponseEntity.ok(new DefaultMessageDTO("Produto deletado com sucesso."));
    }
}
