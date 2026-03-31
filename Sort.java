package sort;

import model.Product;
import java.util.*;

public class Sort {

    //  QUICK SORT → Ascending by Price
    public void sortByAscending(Product arr[], int low, int high) {

        if (low < high) {
            int pi = partition(arr, low, high);

            sortByAscending(arr, low, pi - 1);
            sortByAscending(arr, pi + 1, high);
        }
    }

    int partition(Product arr[], int low, int high) {

        double pivot = arr[high].price;
        int i = low - 1;

        for (int j = low; j < high; j++) {

            if (arr[j].price < pivot) {
                i++;
                swap(arr, i, j);
            }
        }

        swap(arr, i + 1, high);
        return i + 1;
    }

    void swap(Product arr[], int i, int j) {

        Product temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    //  MERGE SORT → Descending by Price
    public void sortByDescending(Product arr[], int low, int high) {

        if (low < high) {
            int mid = (low + high) / 2;

            sortByDescending(arr, low, mid);
            sortByDescending(arr, mid + 1, high);

            merge(arr, low, mid, high);
        }
    }

    void merge(Product arr[], int low, int mid, int high) {

        int i = low, j = mid + 1, k = 0;
        Product temp[] = new Product[high - low + 1];

        while (i <= mid && j <= high) {

            if (arr[i].price > arr[j].price)
                temp[k++] = arr[i++];
            else
                temp[k++] = arr[j++];
        }

        while (i <= mid) temp[k++] = arr[i++];
        while (j <= high) temp[k++] = arr[j++];

        for (int x = low, y = 0; x <= high; x++, y++)
            arr[x] = temp[y];
    }

    //  SELECTION SORT → By Name
    public void sortByName(Product arr[]) {

        for (int i = 0; i < arr.length - 1; i++) {

            int minIndex = i;

            for (int j = i + 1; j < arr.length; j++) {

                if (arr[j].name.compareToIgnoreCase(arr[minIndex].name) < 0) {
                    minIndex = j;
                }
            }

            //  correct swap (outside loop)
            Product temp = arr[i];
            arr[i] = arr[minIndex];
            arr[minIndex] = temp;
        }
    }
 // INSERTION SORT → Sort by ID (Ascending)
    public void sortById(Product arr[]) {

        for (int i = 1; i < arr.length; i++) {

            Product key = arr[i];
            int j = i - 1;

            // shift elements greater than key.id
            while (j >= 0 && arr[j].id > key.id) {
                arr[j + 1] = arr[j];
                j--;
            }

            arr[j + 1] = key; // place key
        }
    }
 // 🔥 APPLY SORT DIRECTLY ON ARRAYLIST (UTILITY METHODS)

 // Default → sort by ID after operations
 public ArrayList<Product> autoSortById(ArrayList<Product> list) {

     Product[] arr = list.toArray(new Product[0]);

     sortById(arr); // your insertion sort

     return new ArrayList<>(Arrays.asList(arr));
 }

 // Sort by Price Asc
 public ArrayList<Product> autoSortByPriceAsc(ArrayList<Product> list) {

     Product[] arr = list.toArray(new Product[0]);

     sortByAscending(arr, 0, arr.length - 1);

     return new ArrayList<>(Arrays.asList(arr));
 }

 // Sort by Price Desc
 public ArrayList<Product> autoSortByPriceDesc(ArrayList<Product> list) {

     Product[] arr = list.toArray(new Product[0]);

     sortByDescending(arr, 0, arr.length - 1);

     return new ArrayList<>(Arrays.asList(arr));
 }

 // Sort by Name
 public ArrayList<Product> autoSortByName(ArrayList<Product> list) {

     Product[] arr = list.toArray(new Product[0]);

     sortByName(arr);

     return new ArrayList<>(Arrays.asList(arr));
 }
}