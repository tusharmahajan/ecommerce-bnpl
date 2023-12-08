package com.example.testdemo.repository;

import com.example.testdemo.models.DueStatus;
import com.example.testdemo.models.ItemDetails;
import com.example.testdemo.models.OrderDetails;
import com.example.testdemo.models.OrderDueDetails;

import java.util.*;

public class OrderRepository {

    private static final Map<String, List<OrderDetails>> orderHistory = new HashMap<>();
    private static final Map<String, List<OrderDetails>> userPendingDues = new HashMap<>();

    public static void storeOrderDetails(String userName, List<ItemDetails> orderItemDetails, Date purchaseDate, int bnplLimit) {
        orderHistory.putIfAbsent(userName, new ArrayList<>());
        userPendingDues.putIfAbsent(userName, new ArrayList<>());
        List<OrderDetails> pendingUserOrders = userPendingDues.get(userName);
        List<OrderDetails> userOrders = orderHistory.get(userName);

        for(ItemDetails x: orderItemDetails){
            OrderDetails orderDetails = new OrderDetails(x.getName(), x.getQuantity(), purchaseDate, bnplLimit);
            userOrders.add(orderDetails);
            pendingUserOrders.add(orderDetails);
        }
    }

    public static void sortOrderByPurchaseDate(List<OrderDetails> orderList) {
        orderList.sort(Comparator.comparing(OrderDetails::getPurchaseDate));
    }

    public static List<OrderDetails> getUserOrderHistory(String userName){
        orderHistory.putIfAbsent(userName, new ArrayList<>());
        sortOrderByPurchaseDate(orderHistory.get(userName));

        return orderHistory.get(userName);
    }

    public static void clearUserPendingDues(String username, List<String> orderIds, Date dateOfClearing) {

        List<OrderDetails> pendingUserOrders = userPendingDues.get(username);
        if(pendingUserOrders == null) return;

        List<OrderDetails> updateUserPendingOrder = new ArrayList<>();

        for(OrderDetails orderDetails: pendingUserOrders){
            if(!orderIds.contains(orderDetails.getOrderId())){
                updateUserPendingOrder.add(orderDetails);
            }
        }

        userPendingDues.put(username, updateUserPendingOrder);
    }

    public static OrderDueDetails getUserDues(String username) {
        List<OrderDetails> pendingUserOrders = userPendingDues.get(username);
        OrderDueDetails orderDueDetails = new OrderDueDetails();
        orderDueDetails.setUserName(username);

        List<OrderDetails> orderDetailsList = new ArrayList<>();

        if(pendingUserOrders == null){
            return orderDueDetails;
        }
        for(OrderDetails orderDetails: pendingUserOrders){
            boolean within30Days = isWithin30Days(orderDetails.getPurchaseDate(), orderDetails.getPurchaseDate());
            if(!within30Days){
                orderDetails.setDueStatus(DueStatus.DELAYED);
            }
            orderDetailsList.add(orderDetails);
        }
        orderDueDetails.setOrderDetailsList(orderDetailsList);
        return orderDueDetails;
    }

    public static boolean isWithin30Days(Date purchaseDate, Date orderDate) {
        long millisecondsInDay = 1000 * 60 * 60 * 24;
        long millisecondsBetween = orderDate.getTime() - purchaseDate.getTime();
        long daysBetween = millisecondsBetween / millisecondsInDay;

        return daysBetween >= 0 && daysBetween <= 30;
    }
}
