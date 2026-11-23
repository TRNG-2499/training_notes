package com.revature.modernjava.records;

import java.math.BigDecimal;

/*
 * This example showcases Static factor methods, extra constructors and
 * implementing interfaces with records
 */
public record Money(BigDecimal amount, String currency)
        implements Comparable<Money> {

    // Extra Static fields are allowed
    public static final String DEFAULT_CURRENCY = "USD";

    // Additional Constructors MUST delegate to the canonical constructor using 'this()'
    public Money(BigDecimal amount) {
        this(amount, DEFAULT_CURRENCY);
    }

    // Static factory method, a common, readable way to create records
    public static Money ofDollars(String value) {
        return new Money(new BigDecimal(value), "USD");
    }

    @Override
    public int compareTo(Money other) {
        return this.amount.compareTo(other.amount);
    }

    public static void main(String[] args) {
        Money price = new Money(new BigDecimal("19.99"), "CAD");
        System.out.println(price);  // Money[amount=19.99, currency=CAD]
        Money tip = new Money(new BigDecimal("4.00"));
        System.out.println(tip);    // Money[amount=4.00, currency=USD]
        Money refund = Money.ofDollars("5.50");
        System.out.println(refund); // Money[amount=5.50, current=USD]

        System.out.println(Money.DEFAULT_CURRENCY); // USD
    }
}
