package com.example.demo.service;

import com.example.demo.models.Category;

import java.util.List;

public interface CategoryService {
    Category create(Category category);

    List <Category> read();
}
