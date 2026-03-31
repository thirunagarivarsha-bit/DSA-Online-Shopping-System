package cart;

import java.util.*;
import model.Product;

public class Cart {

    public LinkedList<Product> cart = new LinkedList<>();
    Stack<Action> undo = new Stack<>();
    Stack<Action> redo = new Stack<>();

    public void add(Product p) {

        if (p == null || p.stock <= 0) {
            System.out.println("Out of stock!");
            return;
        }

        cart.add(p);
        undo.push(new Action(p, "ADD"));
        redo.clear();

        p.stock--;
        System.out.println("Added: " + p.name);
    }

    public void remove(Product p) {

        if (cart.remove(p)) {
            undo.push(new Action(p, "REMOVE"));
            redo.clear();

            p.stock++;
            System.out.println("Removed: " + p.name);
        } else {
            System.out.println("Item not in cart");
        }
    }

    public void undo() {

        if (undo.isEmpty()) {
            System.out.println("Nothing to undo");
            return;
        }

        Action a = undo.pop();

        if (a.type.equals("ADD")) {
            cart.remove(a.product);
            a.product.stock++;
        } else {
            cart.add(a.product);
            a.product.stock--;
        }

        redo.push(a);
        System.out.println("Undo done");
    }

    public void redo() {

        if (redo.isEmpty()) {
            System.out.println("Nothing to redo");
            return;
        }

        Action a = redo.pop();

        if (a.type.equals("ADD")) {
            cart.add(a.product);
            a.product.stock--;
        } else {
            cart.remove(a.product);
            a.product.stock++;
        }

        undo.push(a);
        System.out.println("Redo done");
    }

    public void show() {
        if (cart.isEmpty()) {
            System.out.println("Cart empty");
            return;
        }

        for (Product p : cart)
            System.out.println(p);
    }

    public double total() {
        double sum = 0;
        for (Product p : cart)
            sum += p.price;
        return sum;
    }
}