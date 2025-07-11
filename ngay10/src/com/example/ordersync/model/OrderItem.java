package com.example.ordersync.model;

public class OrderItem {
    private String productId;
    private int quantity;
    private double price;

    public OrderItem() {}

    public OrderItem(String productId, int quantity, double price) {
        this.productId = productId;
        this.quantity = quantity;
        this.price = price;
    }

    // getters & setters
}
