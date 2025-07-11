package com.example.ordersync.processor;

import com.example.ordersync.model.Order;
import com.example.ordersync.service.InventoryService;
import com.example.ordersync.service.ShippingService;

public class OrderProcessor {
    private InventoryService inventoryService;
    private ShippingService shippingService;

    public OrderProcessor(InventoryService inventoryService, ShippingService shippingService) {
        this.inventoryService = inventoryService;
        this.shippingService = shippingService;
    }

    public boolean processOrder(Order order) {
        if (order == null || order.getItems() == null || order.getItems().isEmpty()) {
            System.err.println("Invalid Order!");
            return false;
        }
        boolean available = inventoryService.checkInventory(order);
        if (!available) {
            System.err.println("Inventory check failed!");
            return false;
        }
        shippingService.shipOrder(order);
        return true;
    }
}
