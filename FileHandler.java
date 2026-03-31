package file;

import java.io.*;
import java.util.*;
import model.Product;

public class FileHandler {

    public static ArrayList<Product> loadProducts(String file) throws Exception {

        ArrayList<Product> list = new ArrayList<>();

        BufferedReader br = new BufferedReader(new FileReader(file));
        String line;

        while ((line = br.readLine()) != null) {
            String[] p = line.split(",");

            list.add(new Product(
                    Integer.parseInt(p[0]),
                    p[1],
                    Double.parseDouble(p[2]),
                    Integer.parseInt(p[3])
            ));
        }

        br.close();
        return list;
    }
}