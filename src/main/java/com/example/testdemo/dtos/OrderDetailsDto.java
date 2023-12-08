package com.example.testdemo.dtos;

import com.example.testdemo.models.ItemDetails;
import com.example.testdemo.models.PaymentMode;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class OrderDetailsDto {

    private final String userName;
    private final List<ItemDetails> itemDetails;
    private final PaymentMode paymentMode;
    private final Date purchaseDate;
}
