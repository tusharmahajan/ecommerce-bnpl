package com.example.testdemo.controller;

import com.example.testdemo.dtos.OrderDetailsDto;
import com.example.testdemo.dtos.OrderDueDto;
import com.example.testdemo.dtos.UserDetailsDto;
import com.example.testdemo.models.ItemDetails;
import com.example.testdemo.models.OrderDetails;
import com.example.testdemo.models.OrderDueDetails;
import com.example.testdemo.service.DuePaymentService;
import com.example.testdemo.service.InventoryService;
import com.example.testdemo.service.OrderService;
import com.example.testdemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/portal")
public class FlipkartController {

    @Autowired
    private InventoryService inventoryService;

    @Autowired
    private UserService userService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private DuePaymentService duePaymentService;

    @PostMapping("/seedInventory")
    public String seedInventory(){
        return inventoryService.seedInventory();
    }

    @GetMapping("/viewInventory")
    public List<ItemDetails> viewInventory(){
        return inventoryService.getInventoryDetails();
    }

    @PostMapping("/registerUser")
    public String registerUser(@RequestBody UserDetailsDto userDetailsDto){
        return userService.registerUser(userDetailsDto);
    }

    @PostMapping("/makeOrder")
    public String makeOrder(@RequestBody OrderDetailsDto orderDetailsDto){
        return orderService.makeOrder(orderDetailsDto);
    }

    @GetMapping("/retrieveOrders/{username}")
    public List<OrderDetails> makeOrder(@PathVariable String username){
        return orderService.getUserOrderDetails(username);
    }

    @PostMapping("/clearDues/{username}")
    public String clearDues(@PathVariable String username, @RequestBody OrderDueDto orderDueDto){
        return duePaymentService.clearDues(username, orderDueDto);
    }

    @GetMapping("/viewDues/{username}")
    public OrderDueDetails viewDues(@PathVariable String username){
        return duePaymentService.viewDues(username);
    }
}
