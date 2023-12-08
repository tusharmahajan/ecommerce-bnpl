package com.example.testdemo.repository;

import com.example.testdemo.models.ItemDetails;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class InventoryRepository {

    private final static Map<String, ItemDetails> inventory = new HashMap<>();
    private static final String filePath = "/Users/tusharmahajan/Desktop/IdeaProjects/testdemo/src/main/resources/inventory.txt";



    public static void updateInventory(List<ItemDetails> orderItemDetails) {

        for(ItemDetails itemDetails: orderItemDetails) {
            int quantity = itemDetails.getQuantity();
            ItemDetails inventoryItem = inventory.get(itemDetails.getName());
            inventoryItem.setQuantity(inventoryItem.getQuantity()-quantity);
            inventory.put(itemDetails.getName(), inventoryItem);
        }
    }

    public static void retrieveDataFromFile() {
        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while((line = bufferedReader.readLine()) != null){
                String itemName = line.split(",")[0];
                int price = Integer.parseInt(line.split(",")[1]);
                int quantity = Integer.parseInt(line.split(",")[2]);

                inventory.put(itemName, new ItemDetails(itemName, price, quantity));
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException("File doesn't exist");
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public static List<ItemDetails> getItemDetails() {
        List<ItemDetails> itemDetails = new ArrayList<>();

        for(Map.Entry<String, ItemDetails> x: inventory.entrySet()){
            itemDetails.add(x.getValue());
        }
        return itemDetails;
    }
}
