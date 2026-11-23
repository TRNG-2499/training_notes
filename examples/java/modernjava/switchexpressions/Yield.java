package com.revature.modernjava.switchexpressions;

/*
 * The keyword 'yield' is used in multi-line switch expression
 * to indicate the resultant value for a switch case.
 */
public class Yield {
    public enum ShippingSpeed { STANDARD, EXPRESS, OVERNIGHT }

    public static double shippingCost(ShippingSpeed speed, double weightLbs) {
        return switch (speed) {
            // Single expressions yield their value automatically
            case STANDARD -> 5.99;

            // A block needs "yield" to supply its value
            case EXPRESS -> {
                double base = 9.99;
                double perPound = 0.50 * weightLbs;
                yield base + perPound;
            }

            case OVERNIGHT -> {
                if (weightLbs > 50) {
                    // Throwing is also a valid way to finish a case
                    throw new IllegalArgumentException("Overnight limit is 50 lbs");
                }
                yield 24.99 + (1.25 * weightLbs);
            }
        };
    }


    /*
     * yield works with colon-syntax; however, modern java code typically using
     * arrow-syntax
     */
    public static String sizeLabel(char size) {
        return switch (size) {
            case 'S':
                yield "Small";
            case 'M':
                yield "Medium";
            case 'L':
                yield "Large";
            default:
                yield "Unknown";
        };
    }


    /*
     * 'return' cannot be used inside a switch statement.
     *
     * 'yield' produces a value for the case, and exits the
     * switch case, allowing further execution of code within
     * a method.
     */
    public enum MembershipTier { STANDARD, PREMIUM, VIP }

    public static double checkoutTotal(MembershipTier tier, double subtotal) {
        // "yield" gives its value to the switch, and the switch's value goes into "discount".
        double discount = switch (tier) {
            case STANDARD -> 0.0;

            case PREMIUM -> {
                double rate = 0.10;
                yield subtotal * rate;
            }

            case VIP -> {
                double rate = 0.20;
                double bonus = subtotal > 100 ? 5.00 : 0.00;
                yield (subtotal * rate) + bonus;
            }
        };

        // Now, using the value returned from the switch (discount), we can continue calculations
        double discountedSubtotal = subtotal - discount;
        double tax = discountedSubtotal * 0.08;
        double shipping = discountedSubtotal >= 50 ? 0.00 : 6.99;
        double total = discountedSubtotal + tax + shipping;

        System.out.printf("Subtotal: $%.2f%n", subtotal);
        System.out.printf("Discount: -$%.2f%n", discount);
        System.out.printf("Tax: $%.2f%n", tax);
        System.out.printf("Shipping: $%.2f%n", shipping);
        System.out.printf("Total: $%.2f%n", total);

        return total;
    }

}
