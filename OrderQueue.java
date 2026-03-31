package order;

import java.util.*;
import model.Product;

public class OrderQueue {

    Queue<List<Product>> queue = new LinkedList<>();

    public void placeOrder(List<Product> cart) {
        queue.add(new ArrayList<>(cart));
        System.out.println("Order placed in queue");
    }

    public void process() {

        if (queue.isEmpty()) {
            System.out.println("No orders to process");
            return;
        }

        List<Product> order = queue.poll();

        System.out.println("Processing Order...");
        for (Product p : order) {
            System.out.println(p);
        }

        System.out.println("Order Completed ✅");
    }
}