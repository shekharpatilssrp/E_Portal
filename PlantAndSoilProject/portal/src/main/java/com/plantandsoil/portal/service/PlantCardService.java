package com.plantandsoil.portal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.plantandsoil.portal.model.PlantCard;
import com.plantandsoil.portal.repository.PlantCardRepository;
import java.util.List;
import java.util.Optional;

@Service
public class PlantCardService {

    private final PlantCardRepository plantCardRepository;

    @Autowired
    public PlantCardService(PlantCardRepository plantCardRepository) {
        this.plantCardRepository = plantCardRepository;
    }

    public List<PlantCard> getAllPlants() {
        return plantCardRepository.findAll();
    }

    public Optional<PlantCard> getPlantById(Long id) {
        return plantCardRepository.findById(id);
    }

    public PlantCard savePlant(PlantCard plantCard) {
        return plantCardRepository.save(plantCard);
    }

    public PlantCard updatePlant(Long id, PlantCard updatedPlant) {
        return plantCardRepository.findById(id)
                .map(existingPlant -> {
                    existingPlant.setImageUrl(updatedPlant.getImageUrl());
                    existingPlant.setTitle(updatedPlant.getTitle());
                    existingPlant.setDescription(updatedPlant.getDescription());
                    existingPlant.setMoreInfoUrl(updatedPlant.getMoreInfoUrl());
                    existingPlant.setSoilPhMin(updatedPlant.getSoilPhMin());
                    existingPlant.setSoilPhMax(updatedPlant.getSoilPhMax());
                    return plantCardRepository.save(existingPlant);
                })
                .orElseThrow(() -> new RuntimeException("Plant not found with id: " + id));
    }
    public void deletePlant(Long id) {
        plantCardRepository.deleteById(id);
    }
}
