package com.example.testdemo.models;

import lombok.Data;

@Data
public class ItemDetails {
    private String name;
    private int price;
    private Integer quantity;

    public ItemDetails(String name, int price, Integer quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }
}
