package com.example.testdemo.service;

import com.example.testdemo.dtos.OrderDetailsDto;
import com.example.testdemo.dtos.OrderDueDto;
import com.example.testdemo.factory.PaymentManager;
import com.example.testdemo.factory.PaymentModeFactory;
import com.example.testdemo.models.ItemDetails;
import com.example.testdemo.models.OrderDetails;
import com.example.testdemo.repository.OrderRepository;
import com.example.testdemo.repository.UserRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrderService {

    public String makeOrder(OrderDetailsDto orderDetailsDto) {
        PaymentManager paymentManager = PaymentModeFactory.getPaymentMode(orderDetailsDto);
        return paymentManager.recordOrderPaymentDetails();
    }

    public List<OrderDetails> getUserOrderDetails(String username) {
        return OrderRepository.getUserOrderHistory(username);
    }
}
