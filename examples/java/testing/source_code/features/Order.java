package com.revature.junit.features;

// A simple model representing an order
public class Order {

    private final String customerId;
    private final double subtotal;
    private final int itemCount;

    public Order(String customerId, double subtotal, int itemCount) {
        this.customerId = customerId;
        this.subtotal = subtotal;
        this.itemCount = itemCount;
    }

    public String getCustomerId() { return customerId; }
    public double getSubtotal() { return subtotal; }
    public int getItemCount() { return itemCount; }
}
