package hashing;

import java.util.*;
import model.Product;

public class HashUtil {

    // 🔹 Custom Hash Table (Open Addressing)
    public static class HashTable {

        int SIZE = 100; // keep bigger than number of products
        Product[] table;

        public HashTable() {
            table = new Product[SIZE];
        }

        // 🔹 Hash Function
        int hash(int key) {
            return key % SIZE;
        }

        // 🔹 Insert using Linear Probing
        public void put(Product p) {
            int index = hash(p.id);

            // handle collision
            while (table[index] != null) {
                index = (index + 1) % SIZE;
            }

            table[index] = p;
        }

        // 🔹 Search
        public Product get(int id) {
            int index = hash(id);
            int start = index;

            while (table[index] != null) {

                if (table[index].id == id)
                    return table[index];

                index = (index + 1) % SIZE;

                // stop if full loop
                if (index == start)
                    break;
            }

            return null;
        }
    }

    // 🔹 SAME METHOD NAME (no change in main logic style)
    public static HashTable createMap(ArrayList<Product> list) {

        HashTable map = new HashTable();

        for (Product p : list) {
            map.put(p);
        }

        return map;
    }
}