package com.example.processor;

import com.example.model.Order;
import com.example.service.InventoryService;
import com.example.service.ShippingService;

public class OrderProcessor {
    private final InventoryService inventoryService;
    private final ShippingService shippingService;

    public OrderProcessor(InventoryService inventoryService, ShippingService shippingService) {
        this.inventoryService = inventoryService;
        this.shippingService = shippingService;
    }

    public boolean process(Order order) {
        if (order == null) {
            System.err.println("Invalid Order");
            return false;
        }
        if (inventoryService.checkInventory(order)) {
            shippingService.shipOrder(order);
            return true;
        } else {
            System.err.println("Inventory check failed!");
            return false;
        }
    }
}