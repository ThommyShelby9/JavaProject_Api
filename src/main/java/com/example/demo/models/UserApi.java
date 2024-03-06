package com.example.demo.models;

import java.sql.Date;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.demo.views.Views;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "USER")
public class UserApi implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @JsonProperty(value = "firstname")
    private String firstname;

    @JsonProperty(value = "lastname")
    private String lastname;

    @JsonProperty(value = "password")
    private String password;

    @JsonProperty(value = "email")
    private String email;

    private Boolean accountNonExpired = true;

    private Boolean accountNonLocked = true;

    private Boolean credentialsNonExpired = true;

    private Boolean enabled = false;

    private Date emailVerifyAt;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.firstname;
    }

    @Override
    public boolean isAccountNonExpired() {
        return this.accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return this.enabled;
    }

    public void setPassword() {
    }

    public String getEmail() {
        return this.email;
    }

    public String getLastname() {
        return this.lastname;
    }

    public Date getEmailVerifyAt() {
        return this.emailVerifyAt;
    }

    public boolean isPresent() {
        return false;
    }

    public Date setEmailVerifyAt(Instant now) {
        return this.emailVerifyAt;
    }

    @OneToMany(mappedBy = "user", cascade = CascadeType.PERSIST)
    @JsonManagedReference
    @JsonIgnore
    @JsonView(Views.UniqueView.class)
    private List<Product> products = new ArrayList<>();

    public void addProduct(Product product) {
        if (!this.products.contains(product)) {
            this.products.add(product);
            product.setUser(this);
        }
    }

    public void removeProduct(Product product) {
        if (this.products.contains(product)) {
            this.products.remove(product);
            product.setUser(null); // Supprime l'utilisateur du produit
        }
    }

    public UserApi() {
        // Constructeur sans arguments
    }

    public UserApi(Integer user_id) {
        this.id = user_id;
    }

    //

    // Supprimez la méthode setEmail() car elle ne fait rien actuellement
}
