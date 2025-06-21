package com.plantandsoil.portal.controller;
import java.nio.file.attribute.UserPrincipalNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.plantandsoil.portal.model.User;
import com.plantandsoil.portal.repository.UserRepository;

@Controller
public class ProfileController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/changepassword")
    public String getChangePassword() {
        return "changepassword";
    }
    @PostMapping("/changepassword")
    public String changePassword(@RequestParam String currentPassword, @RequestParam String newPassword, RedirectAttributes redirectAttributes) throws UserPrincipalNotFoundException {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user = userRepository.findByUsername(username);

        if (user == null || !passwordEncoder.matches(currentPassword, user.getPassword())) {

            redirectAttributes.addFlashAttribute("msg", "Password not updated");
            return "redirect:/changepassword";
        }
            String encodedPassword = passwordEncoder.encode(newPassword); 
            user.setPassword(encodedPassword);
            userRepository.save(user);
            redirectAttributes.addFlashAttribute("msg", "Password updated");
            return "redirect:/changepassword";
    }
}

