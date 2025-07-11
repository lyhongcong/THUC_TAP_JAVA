package com.example.ordersync.processor;

import com.example.ordersync.model.Order;
import com.example.ordersync.model.OrderItem;
import com.example.ordersync.service.InventoryService;
import com.example.ordersync.service.ShippingService;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.mockito.Mockito.*;

import java.time.LocalDateTime;
import java.util.Arrays;

@DisplayName("OrderProcessor Unit Test")
public class OrderProcessorTest {

    private InventoryService inventoryService;
    private ShippingService shippingService;
    private OrderProcessor processor;

    @BeforeAll
    static void setupAll() {
        System.out.println("BeforeAll: Setup DB or global resources");
    }

    @AfterAll
    static void tearDownAll() {
        System.out.println("AfterAll: Cleanup DB or global resources");
    }

    @BeforeEach
    void setup() {
        inventoryService = mock(InventoryService.class);
        shippingService = mock(ShippingService.class);
        processor = new OrderProcessor(inventoryService, shippingService);
    }

    @AfterEach
    void tearDown() {
        System.out.println("AfterEach: Cleanup after test");
    }

    @Nested
    @DisplayName("✅ Order Processing Scenarios")
    @Tag("processing")
    class ProcessingTests {

        @Test
        @DisplayName("✔️ Valid order should call Inventory and Shipping")
        void testValidOrder() {
            Order order = new Order("O1", "Alice",
                    Arrays.asList(new OrderItem("P1", 2, 10.0)), 20.0, LocalDateTime.now());

            when(inventoryService.checkInventory(order)).thenReturn(true);

            boolean result = processor.processOrder(order);

            Assertions.assertTrue(result);
            verify(inventoryService, times(1)).checkInventory(order);
            verify(shippingService, times(1)).shipOrder(order);
        }

        @Test
        @DisplayName("❌ Inventory check fails")
        void testInventoryFails() {
            Order order = new Order("O2", "Bob",
                    Arrays.asList(new OrderItem("P1", 2, 10.0)), 20.0, LocalDateTime.now());

            when(inventoryService.checkInventory(order)).thenReturn(false);

            boolean result = processor.processOrder(order);

            Assertions.assertFalse(result);
            verify(inventoryService, times(1)).checkInventory(order);
            verify(shippingService, never()).shipOrder(any());
        }

        @Test
        @DisplayName("❌ Invalid Order is handled gracefully")
        void testInvalidOrder() {
            boolean result = processor.processOrder(null);
            Assertions.assertFalse(result);
        }
    }

    @ParameterizedTest
    @CsvSource({
            "P1, 1, 10.0",
            "P2, 2, 20.0",
            "P3, 5, 5.0"
    })
    @DisplayName("🧮 Parameterized: Multiple OrderItems")
    void testMultipleOrderItems(String productId, int quantity, double price) {
        Order order = new Order("O3", "Charlie",
                Arrays.asList(new OrderItem(productId, quantity, price)), quantity * price, LocalDateTime.now());

        when(inventoryService.checkInventory(order)).thenReturn(true);

        boolean result = processor.processOrder(order);

        Assertions.assertTrue(result);
        verify(inventoryService).checkInventory(order);
        verify(shippingService).shipOrder(order);
    }
}
