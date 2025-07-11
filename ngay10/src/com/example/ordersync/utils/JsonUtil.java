package com.example.ordersync.utils;

import com.example.ordersync.model.Order;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class JsonUtil {
    private static final ObjectMapper mapper = new ObjectMapper();

    static {
        mapper.registerModule(new JavaTimeModule());
    }

    public static String serialize(Order order) throws Exception {
        return mapper.writeValueAsString(order);
    }

    public static Order deserialize(String json) throws Exception {
        return mapper.readValue(json, Order.class);
    }
}
