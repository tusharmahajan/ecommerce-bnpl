package com.example.testdemo.models;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class OrderDueDetails {

    private String userName;
    private List<OrderDetails> orderDetailsList = new ArrayList<>();
}
