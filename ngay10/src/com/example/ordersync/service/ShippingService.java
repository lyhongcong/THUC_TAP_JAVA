package com.example.ordersync.service;

import com.example.ordersync.model.Order;

public interface ShippingService {
    void shipOrder(Order order);
}
