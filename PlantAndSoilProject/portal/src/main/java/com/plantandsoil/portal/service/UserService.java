package com.plantandsoil.portal.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.plantandsoil.portal.model.User;
import com.plantandsoil.portal.repository.UserRepository;

@Service
public class UserService implements UserDetailsService{

   private final UserRepository userRepository;

    private final BCryptPasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    public User registerUser(User user) throws Exception {

        user.setPassword(passwordEncoder.encode(user.getPassword()));
   
        user.setTermsAccepted(true);
 
        return userRepository.save(user);
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public void save(User user) {
        userRepository.save(user);
    }

    public List<User> getAlluser() {
  
        return userRepository.findAll();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
    
        if (user == null) {
          
            throw new UsernameNotFoundException("User not found");
        }
                return new org.springframework.security.core.userdetails.User(
                            user.getUsername(), user.getPassword(), new ArrayList<>());
    }

    public boolean checkByEmail(String email){
    
        return userRepository.existsByEmail(email);
    }

    public boolean checkByUsername(String username) {
 
        return userRepository.existsByUsername(username);
    }
}
