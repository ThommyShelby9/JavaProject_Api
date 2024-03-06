// package com.example.demo.models;

// import java.sql.Date;
// import java.time.Instant;
// import java.util.ArrayList;
// import java.util.List;



// import com.example.demo.views.Views;
// import com.fasterxml.jackson.annotation.JsonInclude;
// import com.fasterxml.jackson.annotation.JsonManagedReference;
// import com.fasterxml.jackson.annotation.JsonView;

// import jakarta.persistence.CascadeType;
// import jakarta.persistence.Entity;
// import jakarta.persistence.GeneratedValue;
// import jakarta.persistence.GenerationType;
// import jakarta.persistence.Id;
// import jakarta.persistence.OneToMany;
// import jakarta.persistence.Table;
// import lombok.Getter;
// import lombok.NoArgsConstructor;
// import lombok.Setter;

// @Entity
// @Getter
// @Setter
// @NoArgsConstructor
// @Table(name = "NEW_USERS")
// @JsonInclude(JsonInclude.Include.ALWAYS)
// public class User {
//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     private Integer id;

//     private String firstname;
//     private String lastname;    
//     private String email;
//     private String password;
//     private Boolean emailVerify = false;
//     private Date emailVerifyAt;

//     @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
//     @JsonManagedReference
//     private List<Product> products = new ArrayList<>();

//     public User(Integer user_id) {
//         this.id = user_id;
//     }

//     public void setEmailVerifyAt(Instant now) {
//         // TODO Auto-generated method stub
//         throw new UnsupportedOperationException("Unimplemented method 'setEmailVerifyAt'");
//     }
// }
