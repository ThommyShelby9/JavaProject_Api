package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import com.example.demo.models.UserApi;

@Repository
public interface UserRepository extends CrudRepository<UserApi, Integer>{

    UserDetails findByFirstname(String firstname);

    Optional<UserApi> findByEmail(String email);

    UserApi findByPassword(String password);

    Optional<UserApi> findById(Long userId);
   
}
