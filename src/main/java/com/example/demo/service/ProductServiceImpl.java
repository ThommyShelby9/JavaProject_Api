package com.example.demo.service;

import com.example.demo.models.Category;
import com.example.demo.models.Product;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.repository.ProductRepository;
import com.example.demo.views.Views;
import com.fasterxml.jackson.annotation.JsonView;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;


@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    @Override
    public Product create(Product product, Long idCat) {
        @SuppressWarnings("null")
        Category category = categoryRepository.findById(idCat)
                .orElseThrow(() -> new EntityNotFoundException("Category not found with id: " + idCat));
        product.setCategory(category);
        return productRepository.save(product);
    }

    @Override
    @JsonView(Views.ProductWithCategory.class)
    public List<Product> read() {
        return productRepository.findAll();
    }
    @SuppressWarnings("null")
    @Override
    public Optional<Product> modify(Long id, Product product) {
        return Optional.ofNullable(productRepository.findById(id)
                .map(p -> {
                    if (product.getPrice() != null) {
                        p.setPrice(product.getPrice());
                    }
                    if (product.getName() != null) {
                        p.setName(product.getName());
                    }
                    if (product.getDescription() != null) {
                        p.setDescription(product.getDescription());
                    }
                    if (product.getCategory() != null) {
                        p.setCategory(product.getCategory());
                    }
                    return productRepository.save(p);
                }).orElseThrow(() -> new EntityNotFoundException("Product non trouvé!")));
    }
    

    @SuppressWarnings("null")
    @Override
    public String delete(Long id) {
        productRepository.deleteById(id);
        return "Produit supprimé avec succès";
    }

    @SuppressWarnings("null")
    @Override
    public Product create(Product product) {
        return productRepository.save(product);
    }

    @SuppressWarnings("null")
    @Override
    public Optional<Product> find(Long id) {
        return productRepository.findById(id);
    }
}
