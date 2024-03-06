package com.example.demo.models;

import com.example.demo.views.Views;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonView;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "PRODUIT")
@JsonInclude(JsonInclude.Include.ALWAYS)
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(Views.ProductWithCategory.class)
    private Long id;

    @Column(unique = true)
    @JsonView(Views.ProductWithCategory.class)
    private String name;
    @JsonView(Views.ProductWithCategory.class)
    private String description;
    @JsonView(Views.ProductWithCategory.class)
    private Integer price;

    @ManyToOne(fetch = FetchType.EAGER)
@JsonBackReference(value = "category")
@JoinColumn(name = "id_cat")
private Category category;


    public void setCategory(Category category) {
        if (this.category != null) {
            this.category.getProducts().remove(this);
        }
        this.category = category;
        category.getProducts().add(this);
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonBackReference
    @JoinColumn(name = "userId")
    private UserApi user;

    public void setUser(UserApi user) {
        if (this.user != null) {
            this.user.getProducts().remove(this); // Supprime le produit de la liste des produits de l'ancien utilisateur
        }
        this.user = user;
        if (user != null) {
            user.addProduct(this); // Ajoute le produit à la liste des produits du nouvel utilisateur
        }
    }
}
