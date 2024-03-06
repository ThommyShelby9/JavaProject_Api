package com.example.demo.controllers;

import com.example.demo.models.Product;
import com.example.demo.service.ProductService;
import com.example.demo.views.Views;
import com.fasterxml.jackson.annotation.JsonView;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/")
@AllArgsConstructor
public class ProduitController {
    private final ProductService productService;

    @PostMapping("/create/{idCat}")
    public ResponseEntity<Product> createProduct(@RequestBody Product product, @PathVariable Long idCat) {
        Product createdProduct = productService.create(product, idCat);
        return new ResponseEntity<>(createdProduct, HttpStatus.CREATED);
    }
    

    @JsonView(Views.ProductWithCategory.class)
    @GetMapping("/product")
    public List<Product> read() {
        List<Product> products = productService.read();
        return products;
    }

    @JsonView(Views.ProductWithCategory.class)
    @GetMapping("/product/{id}")
    public Optional<Product> getProductById(@PathVariable Long id) {
        try {
            Optional<Product> product = productService.find(id);

            if (product != null) {
                return product;
            } else {
                throw new ProductNotFoundException("Product not found with ID: " + id);
            }
        } catch (Exception e) {
            throw new RuntimeException("Error fetching product details: " + e.getMessage());
        }
    }


    @PutMapping("/update/{id}")
    public Optional<Product> update(@PathVariable Long id, @RequestBody Product produit) {
        return productService.modify(id, produit);
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.OPTIONS)
    @ResponseStatus(HttpStatus.OK)
    public void handleOptions(@PathVariable Long id) {

    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        return productService.delete(id);
    }
}
