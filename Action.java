package cart;

import model.Product;

public class Action {
    Product product;
    String type; // "ADD" or "REMOVE"

    public Action(Product p, String t) {
        this.product = p;
        this.type = t;
    }
}