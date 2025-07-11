package com.example.service;

import com.example.model.Order;

public interface InventoryService {
    boolean checkInventory(Order order);
}