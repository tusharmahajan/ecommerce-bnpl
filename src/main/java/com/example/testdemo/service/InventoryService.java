package com.example.testdemo.service;

import com.example.testdemo.models.ItemDetails;

import java.util.List;

public interface InventoryService {

    String seedInventory();

    List<ItemDetails> getInventoryDetails();
}
