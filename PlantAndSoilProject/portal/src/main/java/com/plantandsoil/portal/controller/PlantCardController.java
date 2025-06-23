package com.plantandsoil.portal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.plantandsoil.portal.model.PlantCard;
import com.plantandsoil.portal.service.PlantCardService;

@Controller
@RequestMapping("/plants")
public class PlantCardController {

    private final PlantCardService plantCardService;

    @Autowired
    public PlantCardController(PlantCardService plantCardService) {
        this.plantCardService = plantCardService;
    }

    // Show form
    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("plantCard", new PlantCard());
        return "add-plant";
    }

    // Process form submission
    @PostMapping("/add")
    public String addPlant(@ModelAttribute PlantCard plantCard) {
        plantCardService.savePlant(plantCard);
        return "redirect:/plants";
    }

    // Display all plant cards
    @GetMapping
    public String listPlants(Model model) {
        model.addAttribute("plants", plantCardService.getAllPlants());
        return "plant-list";
    }
}
