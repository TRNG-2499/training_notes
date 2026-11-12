package com.revature.logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OrderProcessor {

    // A static reference to a Logger (static so that it is available across any instance of
    // our 'OrderProcessor' if we create multiple objects
    // Loggers are created using the Logback LoggerFactory
    private final Logger logger = LoggerFactory.getLogger(OrderProcessor.class);

    private static final int LOW_STOCK_THRESHOLD = 25;

    public void processOrder(String orderId, int quantity) {
        logger.trace("processOrder method was invoked");

        // DEBUG: fine-grained detail, useful during development/troubleshooting,
        // typically filtered out in production unless actively debugging
        logger.debug("Received request to process order {} with quantity {}", orderId, quantity);

        // Basic validation -- an invalid quantity is a real problem worth an ERROR
        if (quantity <= 0) {
            // ERROR: something went wrong that prevents normal operation from continuing
            logger.error("Failed to process order {} -- invalid quantity: {}", orderId, quantity);
            return; // stop processing this order
        }

        // Simulate checking inventory
        int simulatedStockLevel = calculateSimulatedStock(quantity);

        if (simulatedStockLevel < LOW_STOCK_THRESHOLD) {
            // WARN: not an error, but something worth flagging --
            // processing continues normally despite this condition
            logger.warn("Stock running low for order {} -- remaining stock: {}", orderId, simulatedStockLevel);
        }

        // INFO: high-level application flow -- the kind of event you'd want
        // visible in production logs under normal operation
        logger.info("Order {} processed successfully. Quantity: {}", orderId, quantity);

        // TRACE: even more granular than DEBUG -- rarely enabled, used for
        // very detailed step-by-step tracing when diagnosing tricky issues
        logger.trace("Internal state after processing order {}: stockLevel={}", orderId, simulatedStockLevel);

        logger.trace("processOrder method has completed");
    }

    private int calculateSimulatedStock(int quantityRequested) {
        // Just a stand-in calculation for demonstration purposes
        return 100 - (quantityRequested * 2);
    }
}
