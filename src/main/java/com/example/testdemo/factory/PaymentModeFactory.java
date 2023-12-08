package com.example.testdemo.factory;

import com.example.testdemo.dtos.OrderDetailsDto;
import com.example.testdemo.models.PaymentMode;

public class PaymentModeFactory {


    public static PaymentManager getPaymentMode(OrderDetailsDto orderDetailsDto) {
        PaymentMode paymentMode = orderDetailsDto.getPaymentMode();

        if(paymentMode.equals(PaymentMode.BNPL)){
            return new BNPLPaymentManager(orderDetailsDto.getUserName(), orderDetailsDto.getItemDetails(), orderDetailsDto.getPurchaseDate());
        }
        else return new PrePaidPaymentManager(orderDetailsDto.getUserName(), orderDetailsDto.getItemDetails(), orderDetailsDto.getPurchaseDate());
    }
}
