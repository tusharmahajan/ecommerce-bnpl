package com.example.testdemo.factory;

import com.example.testdemo.models.ItemDetails;

import java.util.Date;
import java.util.List;

public class PrePaidPaymentManager implements PaymentManager {

    private final String userName;
    private final List<ItemDetails> itemDetails;
    private final Date purchaseDate;

    public PrePaidPaymentManager(String userName, List<ItemDetails> itemDetails, Date purchaseDate) {
        this.userName = userName;
        this.itemDetails = itemDetails;
        this.purchaseDate = purchaseDate;
    }

    @Override
    public String recordOrderPaymentDetails() {
        return null;
    }
}
