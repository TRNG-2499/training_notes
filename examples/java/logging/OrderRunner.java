package com.revature.logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OrderRunner {

    // Logger is typically created as a private static final field,
    // scoped to the class it's declared in (using .class ensures
    // the logger name matches the fully qualified class name)
    private static final Logger logger = LoggerFactory.getLogger(OrderRunner.class);

    public static void main(String[] args) {

        logger.info("Application starting up...");

        OrderProcessor processor = new OrderProcessor();

        // A successful order -- demonstrates the "happy path" logging
        processor.processOrder("ORD-1001", 3);

        // An order that triggers a warning (low stock)
        processor.processOrder("ORD-1002", 50);

        // An order that triggers an error (invalid quantity)
        processor.processOrder("ORD-1003", -5);

        logger.info("Application finished processing all orders.");
    }
}