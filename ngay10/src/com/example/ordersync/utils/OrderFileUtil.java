package com.example.ordersync.utils;

import com.example.ordersync.model.Order;

import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;

public class OrderFileUtil {

    public static void saveToFile(Order order, String filePath) throws Exception {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
            oos.writeObject(order);
        }
    }

    public static Order loadFromFile(String filePath) throws Exception {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))) {
            return (Order) ois.readObject();
        }
    }
}
