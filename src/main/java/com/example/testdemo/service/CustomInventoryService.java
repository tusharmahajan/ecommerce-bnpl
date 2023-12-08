package com.example.testdemo.service;

import com.example.testdemo.models.ItemDetails;
import com.example.testdemo.repository.InventoryRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CustomInventoryService implements InventoryService {

    @Override
    public String seedInventory() {
        InventoryRepository.retrieveDataFromFile();
        return "Successfully stored the data";
    }

    @Override
    public List<ItemDetails> getInventoryDetails() {
        return InventoryRepository.getItemDetails();
    }

}
