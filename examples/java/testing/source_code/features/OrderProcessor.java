package com.revature.junit.features;

/*
 * Class representing the business logic of an application
 */
public class OrderProcessor {

    private static final double BULK_DISCOUNT_THRESHOLD = 100.00;
    private static final double BULK_DISCOUNT_RATE = 0.10; // 10% off
    private static final int MAX_ITEM_COUNT = 50;

    // Order total is the current SubTotal minus discounts
    public double calculateTotal(Order order) {
        if (order.getSubtotal() < 0) {
            throw new IllegalArgumentException("Subtotal cannot be negative");
        }

        double discount = calculateDiscount(order.getSubtotal());
        return order.getSubtotal() - discount;
    }

    // Give a discount if someone orders items above discount threshold
    public double calculateDiscount(double subtotal) {
        if (subtotal >= BULK_DISCOUNT_THRESHOLD) {
            return subtotal * BULK_DISCOUNT_RATE;
        }
        return 0.0;
    }

    // Validation Check
    public boolean isValidOrder(Order order) {
        // Order is actually associated with a customer
        if (order.getCustomerId() == null || order.getCustomerId().isBlank()) {
            return false;
        }

        // Cannot place order if Item count is 0 or less, or total item count is too high
        if (order.getItemCount() <= 0 || order.getItemCount() > MAX_ITEM_COUNT) {
            return false;
        }
        return true;
    }
}
