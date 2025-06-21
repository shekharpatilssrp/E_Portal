package com.plantandsoil.portal.model;


import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name = "user")

public class User implements UserDetails{

 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 private Long id;

 private String username;

 private String email;

 private String password;

 private Boolean termsAccepted = false;

 private String role = "USER";


//   public User(Long id, String username, String password, String email, String role) {
//         this.id = id;
//         this.username = username;
//         this.password = password;
//         this.email = email;
//         this.role = role;
//     }

    public Collection<? extends GrantedAuthority> getAuthorities() {
    return Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + this.role));
}

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
       
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
      
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
       
        return true;
    }

    @Override
    public boolean isEnabled() {
        
        return true;
    }

 public Long getId() {
    return id;
 }


 public void setId(Long id) {
    this.id = id;
 }


//  public String getUsername() {
//     return username;
//  }


 public void setUsername(String username) {
    this.username = username;
 }


 public String getEmail() {
    return email;
 }


 public void setEmail(String email) {
    this.email = email;
 }


//  public String getPassword() {
//     return password;
//  }


 public void setPassword(String password) {
    this.password = password;
 }

  public Boolean getTermsAccepted() {
        return termsAccepted;
    }

    public void setTermsAccepted(Boolean termsAccepted) {
        this.termsAccepted = termsAccepted;
    }

        public String getRole() {
        return role;
    }
       public User setRole(String role) {
        this.role = role;
          return this;
    }

}
