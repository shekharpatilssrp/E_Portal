package com.plantandsoil.portal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.plantandsoil.portal.model.User;



@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    
    User findByUsername(String username);


    User findByEmail(String email);
    
    boolean existsByUsername(String username);



    boolean existsByEmail(String email);

}
