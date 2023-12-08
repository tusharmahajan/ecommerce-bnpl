package com.example.testdemo.factory;

import com.example.testdemo.models.ItemDetails;
import com.example.testdemo.repository.InventoryRepository;
import com.example.testdemo.repository.OrderRepository;
import com.example.testdemo.repository.UserRepository;

import java.util.Date;
import java.util.List;

public class BNPLPaymentManager implements PaymentManager {

    private final String userName;
    private final List<ItemDetails> itemDetails;
    private final Date purchaseDate;

    public BNPLPaymentManager(String userName, List<ItemDetails> itemDetails, Date purchaseDate) {
        this.userName = userName;
        this.itemDetails = itemDetails;
        this.purchaseDate = purchaseDate;
    }

    @Override
    public String recordOrderPaymentDetails() {

        List<ItemDetails> inventoryItems = InventoryRepository.getItemDetails();
        int userBnplLimit = UserRepository.getUserBnplLimit(userName);

        boolean isOrderPossible = validateAndMakeOrder(userName, userBnplLimit, inventoryItems, this.itemDetails);
       if(isOrderPossible){
        return "Order created";
       }

       return "Order not possible";
    }

    private boolean validateAndMakeOrder(String userName, int userBnplLimit, List<ItemDetails> inventoryItems, List<ItemDetails> orderItemDetails) {
        int totalOrderValue = 0;
        boolean isOrderPossible = true;

        for(ItemDetails x: orderItemDetails){
            for(ItemDetails y: inventoryItems){
                if(x.getName().equals(y.getName())){
                    totalOrderValue+=(y.getPrice()*x.getQuantity());
                    if(x.getQuantity() > y.getQuantity()){
                        isOrderPossible = false;
                        break;
                    }
                }
            }
        }

        if(!isOrderPossible || (userBnplLimit < totalOrderValue)) return false;

        InventoryRepository.updateInventory(orderItemDetails);
        UserRepository.updateUserBNPLLimit(userName, userBnplLimit-totalOrderValue);
        OrderRepository.storeOrderDetails(userName, orderItemDetails, purchaseDate, userBnplLimit-totalOrderValue);
        return true;
    }
}
