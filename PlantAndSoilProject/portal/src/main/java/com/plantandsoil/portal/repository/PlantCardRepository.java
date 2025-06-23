package com.plantandsoil.portal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.plantandsoil.portal.model.PlantCard;

@Repository
public interface PlantCardRepository extends JpaRepository<PlantCard, Long> {
    // You can add custom query methods here if needed
}