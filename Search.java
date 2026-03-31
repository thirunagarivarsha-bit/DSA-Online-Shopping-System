package search;

import java.util.*;
import model.Product;

public class Search {

    public static Product linearById(ArrayList<Product> list, int id) {
        for (Product p : list)
            if (p.id == id) return p;
        return null;
    }

    public static Product binaryByName(ArrayList<Product> list, String key) {
        int l = 0, r = list.size() - 1;

        while (l <= r) {
            int mid = (l + r) / 2;

            int cmp = list.get(mid).name.compareToIgnoreCase(key);

            if (cmp == 0) return list.get(mid);
            else if (cmp < 0) l = mid + 1;
            else r = mid - 1;
        }
        return null;
    }
}