package com.example.demo.models;

import com.example.demo.views.Views;
import com.example.demo.views.Views;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonView;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "CATEGORY")
@JsonInclude(JsonInclude.Include.ALWAYS)
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(Views.UniqueView.class)
    private Long idCat;

    @Column(unique = true)
    @JsonView(Views.UniqueView.class)
    private String nameCat;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonManagedReference(value = "category")
    @JsonView(Views.UniqueView.class)
    private List<Product> products = new ArrayList<>();

    public Category(Long idCat) {
        this.idCat = idCat;
    }

    public Category() {
        // Constructeur par défaut sans paramètres
    }

}
