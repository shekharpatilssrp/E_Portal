package com.plantandsoil.portal.controller;
import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import com.plantandsoil.portal.model.User;

import com.plantandsoil.portal.service.UserService;

import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
public class HomeController {

    @Autowired
    private UserService userService;

    @GetMapping("/index")
    public String showIndexPage(){
        return "index";
    }

    @GetMapping("/plant")
    public String showPlantPage(){
        return "plant";
    }
    
     @GetMapping("/soil")
    public String showSoilPage(){
        return "soil";
    }

     @GetMapping("/soiltest")
    public String showSoiltestPage(){
        return "soiltest";
    }

     @GetMapping("/about")
    public String showAboutPage(){
        return "about";
    }
    @GetMapping("/contact")
    public String showContactPage(){
        return "contact";
    }
    
     @GetMapping("/login")
     public String showloginPage(){
        return "login";
    }

    @GetMapping("/register")
     public String showRegisterPage(){
        return "register";
    }

    @PostMapping("/createUser")
    public String createuser(@ModelAttribute User user, RedirectAttributes redirectAttributes) throws Exception {
    

        boolean e = userService.checkByEmail(user.getEmail());
        boolean u = userService.checkByUsername(user.getUsername());
        if(!u){
            if(!e) {
                            User userInput =  userService.registerUser(user);
                                // System.out.println(user);
                                if(userInput != null){
                                       redirectAttributes.addFlashAttribute("msg", "Registration Successfull");
                                        
                            }else{
                                redirectAttributes.addFlashAttribute("msg", "Somthing error in registration");
                                
                            }    
            }else{redirectAttributes.addFlashAttribute("msg", "Email is already exists");
        }    
        }else{
            redirectAttributes.addFlashAttribute("msg", "Username is already exists");        
        }  
        return "redirect:/register";
    }
}
