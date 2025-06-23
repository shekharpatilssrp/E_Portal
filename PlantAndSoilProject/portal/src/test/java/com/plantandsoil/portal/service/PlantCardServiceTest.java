package com.plantandsoil.portal.service;


import com.plantandsoil.portal.model.PlantCard;
import com.plantandsoil.portal.repository.PlantCardRepository;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class PlantCardServiceTest {

    @Mock
    private PlantCardRepository plantCardRepository;

    @InjectMocks
    private PlantCardService plantCardService;

    @Test
    public void testGetAllPlants() {
        PlantCard plant1 = new PlantCard("img1.jpg", "Plant1", "Desc1", "url1", 5.5, 7.0);
        PlantCard plant2 = new PlantCard("img2.jpg", "Plant2", "Desc2", "url2", 6.0, 7.5);
        when(plantCardRepository.findAll()).thenReturn(Arrays.asList(plant1, plant2));

        List<PlantCard> plantList = plantCardService.getAllPlants();

        assertEquals(2, plantList.size());
        assertEquals("Plant1", plantList.get(0).getTitle());
    }
}
