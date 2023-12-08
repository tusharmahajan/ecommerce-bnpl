package com.example.testdemo.service;

import com.example.testdemo.dtos.OrderDueDto;
import com.example.testdemo.models.OrderDueDetails;
import com.example.testdemo.repository.OrderRepository;
import org.springframework.stereotype.Component;

@Component
public class DuePaymentService {

    public String clearDues(String username, OrderDueDto orderDueDto) {
        OrderRepository.clearUserPendingDues(username, orderDueDto.getOrderId(), orderDueDto.getDateOfClearing());
        return "Due clearance successful";
    }

    public OrderDueDetails viewDues(String username) {
        return OrderRepository.getUserDues(username);
    }
}
