package com.example.testdemo.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class OrderDueDto {

    private List<String> orderId;

    @JsonProperty("date_of_clearing")
    private Date dateOfClearing;
}
