package util;

public class Discount {

    public static double apply(double total) {

        if (total > 50000) {
            System.out.println("50% Discount Applied");
            return total * 0.5;
        } else if (total > 40000) {
            System.out.println("40% Discount Applied");
            return total * 0.6;
        } else if (total > 30000) {
            System.out.println("30% Discount Applied");
            return total * 0.7;
        } else if (total > 20000) {
            System.out.println("20% Discount Applied");
            return total * 0.8;
        } else if (total > 10000) {
            System.out.println("10% Discount Applied");
            return total * 0.9;
        }

        return total;
    }
}