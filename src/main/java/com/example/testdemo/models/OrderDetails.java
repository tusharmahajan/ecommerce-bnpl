package com.example.testdemo.models;

import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Data
public class OrderDetails {

    private String orderId = UUID.randomUUID().toString();
    private String name;
    private Integer quantity;
    private Date purchaseDate;
    private int bnplLimit;
    private DueStatus dueStatus = DueStatus.PENDING;

    public OrderDetails(String name, Integer quantity, Date purchaseDate, int bnplLimit) {
        this.name = name;
        this.quantity = quantity;
        this.purchaseDate = purchaseDate;
        this.bnplLimit = bnplLimit;
    }

}
