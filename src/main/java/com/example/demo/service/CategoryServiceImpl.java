package com.example.demo.service;

import com.example.demo.models.Category;
import com.example.demo.models.Product;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.views.Views;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CategoryServiceImpl implements CategoryService{

    private final CategoryRepository categoryRepository;
    @SuppressWarnings("null")
    @Override
    public Category create(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    @JsonView(Views.UniqueView.class)
    public List<Category> read() {
        return categoryRepository.findAll();
    }


    public void exampleUsage() {

        Product produit = new Product();
        produit.setName("Nom du produit");
        produit.setDescription("Description du produit");
        produit.setPrice(123);

        Category category = new Category(null);
        category.setNameCat("Nom de la catégorie");

        produit.setCategory(category);

        category.getProducts().add(produit);

        categoryRepository.save(category);
    }
}
