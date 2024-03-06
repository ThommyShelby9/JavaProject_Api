package com.example.demo.repository;
import org.springframework.data.repository.CrudRepository;
import com.example.demo.models.Validation;

public interface ValidationRepository extends CrudRepository <Validation, Integer> {
    
    Validation findByCode(String code);
}
