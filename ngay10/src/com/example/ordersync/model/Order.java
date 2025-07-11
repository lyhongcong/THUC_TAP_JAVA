package com.example.ordersync.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

public class Order implements Serializable {
    private static final long serialVersionUID = 1L;

    private String orderId;

    @JsonProperty("customer")
    private String customerName;

    private List<OrderItem> items;

    private double totalPrice;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdAt;

    @JsonIgnore
    private String internalNote;

    public Order() {}

    public Order(String orderId, String customerName, List<OrderItem> items,
                 double totalPrice, LocalDateTime createdAt) {
        this.orderId = orderId;
        this.customerName = customerName;
        this.items = items;
        this.totalPrice = totalPrice;
        this.createdAt = createdAt;
    }

    // getters & setters
}
