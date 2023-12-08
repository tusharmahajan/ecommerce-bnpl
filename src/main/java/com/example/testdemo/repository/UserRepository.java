package com.example.testdemo.repository;

import com.example.testdemo.models.ItemDetails;
import com.example.testdemo.models.OrderDetails;
import lombok.Data;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
public class UserRepository {

    private static final Map<String, Integer> userDetails = new HashMap<>();

    public static String storeUserDetails(String name, Integer bnplLimit) {
        userDetails.put(name, bnplLimit);
        return "Successfully stored user details";
    }

    public static Integer getUserBnplLimit(String name){
        return userDetails.getOrDefault(name, 0);
    }

    public static void updateUserBNPLLimit(String name, int value) {
        userDetails.put(name, value);
    }

}
