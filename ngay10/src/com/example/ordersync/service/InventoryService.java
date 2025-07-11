package com.example.ordersync.service;

import com.example.ordersync.model.Order;

public interface InventoryService {
    boolean checkInventory(Order order);
}
