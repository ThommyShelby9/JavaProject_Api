package com.example.demo.service;

import com.example.demo.models.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
   Product create(Product produit);
   Product create(Product produit, Long idCat);  // Nouvelle méthode avec l'identifiant de la catégorie

   List<Product> read();
   Optional<Product> find(Long id);

   Optional<Product> modify(Long Id, Product produit);
   String delete(Long Id);
}

 